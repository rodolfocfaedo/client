package com.rodolfocf.client.controller;


import com.rodolfocf.client.business.ClientService;
import com.rodolfocf.client.business.dto.CellphoneDTO;
import com.rodolfocf.client.business.dto.ClientDTO;
import com.rodolfocf.client.infrastructure.entity.Client;
import com.rodolfocf.client.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping
    public ResponseEntity <Client> saveClient(@RequestBody Client client){
        return ResponseEntity.ok(clientService.saveClient(client));
    }

    @PostMapping("/login")
    public String login(@RequestBody ClientDTO clientDTO){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(clientDTO.getEmail(),
                clientDTO.getPassword()));
        return "Bearer " + jwtUtil.generateToken(authentication.getName());
    }

    @PostMapping("/cellphone")
    public ResponseEntity<CellphoneDTO> cellphoneRegister(@RequestBody CellphoneDTO cellphoneDTO, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(clientService.cellphoneRegister(token, cellphoneDTO));
    }

//-----------------------------------------------------------------------------------------------------------------------------------------

    @GetMapping
    public ResponseEntity<ClientDTO> searchClientByEmail(@RequestParam("email") String email){
        return ResponseEntity.ok(clientService.searchClientByEmail(email));
    }

//-----------------------------------------------------------------------------------------------------------------------------------------

    @PutMapping
    public ResponseEntity<ClientDTO> updateClientData(@RequestBody ClientDTO clientDTO, @RequestHeader("Authorization") String token){
        return ResponseEntity.ok(clientService.updateClientData(token, clientDTO));
    }

    @PutMapping("/cellphone")
    public ResponseEntity<CellphoneDTO> cellphoneUpdate(@RequestBody CellphoneDTO cellphoneDTO, @RequestParam("id") Long id){
        return ResponseEntity.ok(clientService.cellphoneUpdate(id, cellphoneDTO));
    }

//-----------------------------------------------------------------------------------------------------------------------------------------

    @DeleteMapping("/{email}")
    public ResponseEntity<Void> deleteClientByEmail(@PathVariable("email") String email){
        clientService.deleteClientByEmail(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/cellphones/{cellphoneId}")
    public ResponseEntity<Void> deleteCellphone(@PathVariable("cellphoneId") Long Id,@RequestHeader("Authorization") String token) {
        clientService.deleteCellphone(Id);
        return ResponseEntity.ok().build();
    }
    }


