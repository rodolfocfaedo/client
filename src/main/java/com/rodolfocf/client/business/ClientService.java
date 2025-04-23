package com.rodolfocf.client.business;

import com.rodolfocf.client.business.converter.ClientConverter;
import com.rodolfocf.client.business.dto.CellphoneDTO;
import com.rodolfocf.client.business.dto.ClientDTO;
import com.rodolfocf.client.infrastructure.entity.Cellphone;
import com.rodolfocf.client.infrastructure.entity.Client;
import com.rodolfocf.client.infrastructure.exceptions.ConflictException;
import com.rodolfocf.client.infrastructure.exceptions.ResourceNotFoundException;
import com.rodolfocf.client.infrastructure.repository.CellphoneRepository;
import com.rodolfocf.client.infrastructure.repository.ClientRepository;
import com.rodolfocf.client.infrastructure.security.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final ClientConverter clientConverter;
    private final CellphoneRepository cellphoneRepository;

    public Client saveClient(Client client){
        try {
            emailExists(client.getEmail());
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            return clientRepository.save(client);
        } catch (ConflictException e) {
            throw new ConflictException("Email is already registered");
        }
    }

    public void emailExists(String email){
        try {
            boolean exist = checkExistingEmail(email);
            if(exist){
                throw new ConflictException("Email is already registered");
            }
        }catch (ConflictException e){
            throw new ConflictException("Email is already registered");
        }
    }

    public boolean checkExistingEmail(String email){
        return clientRepository.existsByEmail(email);
    }

    public ClientDTO searchClientByEmail(String email){
        try {
            return clientConverter.toClientDTO(clientRepository.findByEmail(email).orElseThrow(
                    () -> new ResourceNotFoundException("Email " + email + " not found or not registered")));
        }catch (ResourceNotFoundException e){
            throw new ResourceNotFoundException("Email " + email + " not found or not registered");
        }

    }

    public void  deleteClientByEmail(String email){
        clientRepository.deleteClientByEmail(email);

    }

//------------------------------------------------------ UPDATES -----------------------------------------------------------------------------

    public ClientDTO updateClientData(String token, ClientDTO dto){
        //Search for the user's email through the token(remove the email requirement)
        String email = jwtUtil.extractEmailFromToken(token.substring(7));

        //Password encryption
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null );

        //Search for the user data in the database
        Client clientEntity = clientRepository.findByEmail(email).orElseThrow( () -> new ResourceNotFoundException("Email" + email + " not found"));

        //Merge the data received in the DTO request with database data
        Client client = clientConverter.clientUpdate(dto, clientEntity);

        //Saved the converted user data and then got the return and converted it to PersonDTO return
        return clientConverter.toClientDTO(clientRepository.save(client));

    }

    public CellphoneDTO cellphoneUpdate(Long cellphoneId, CellphoneDTO cellphoneDTO){
        Cellphone entity = cellphoneRepository.findById(cellphoneId).orElseThrow( () -> new ResourceNotFoundException
                ("Id " + cellphoneId + " not found"));
        Cellphone cellphone = clientConverter.cellphoneUpdate(cellphoneDTO, entity);
        return clientConverter.toCellphoneDTO(cellphoneRepository.save(cellphone));
    }

//-------------------------------------------------------------------------------------------------------------------------------------

    public CellphoneDTO cellphoneRegister(String token, CellphoneDTO cellphoneDTO){
        String email = jwtUtil.extractEmailFromToken(token.substring(7));
        Client client = clientRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email " + email + " not found or not registered"));
        Cellphone cellphone = clientConverter.toCellphone(cellphoneDTO, client.getId());
        Cellphone cellphoneEntity = cellphoneRepository.save(cellphone);
        return clientConverter.toCellphoneDTO(cellphoneEntity);
    }

    @Transactional
    public void deleteCellphone(Long Id) {
        cellphoneRepository.deleteById(Id);
    }




}
