package com.rodolfocf.client.business.converter;

import com.rodolfocf.client.business.dto.CellphoneDTO;
import com.rodolfocf.client.business.dto.ClientDTO;
import com.rodolfocf.client.infrastructure.entity.Cellphone;
import com.rodolfocf.client.infrastructure.entity.Client;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientConverter {

//----------------------------------------------------------DTO to ENTITY---------------------------------------------------------------------------

    public Client toClientEntity(ClientDTO clientDTO){
        return Client.builder()
                .name(clientDTO.getName())
                .email(clientDTO.getEmail())
                .password(clientDTO.getPassword())
                .cellphones(toListCellphoneEntity(clientDTO.getCellphones()))
                .build();
    }

    public List<Cellphone> toListCellphoneEntity(List<CellphoneDTO> cellphoneDTO){
        return cellphoneDTO.stream().map(this :: toCellphoneEntity).toList();
    }

    public Cellphone toCellphoneEntity(CellphoneDTO cellphoneDTO){
        return Cellphone.builder()
                .number(cellphoneDTO.getNumber())
                .areaCode(cellphoneDTO.getAreaCode())
                .build();
    }

    //----------------------------------------------------------ENTITY to DTO----------------------------------------------------------------------

    public ClientDTO toClientDTO(Client client){
        return ClientDTO.builder()
                .name(client.getName())
                .email(client.getEmail())
                .password(client.getPassword())
                .cellphones(toListCellphoneDTO(client.getCellphones()))
                .build();
    }

    public List<CellphoneDTO> toListCellphoneDTO(List<Cellphone> cellphone){
        return cellphone.stream().map(this :: toCellphoneDTO).toList();
    }


    public CellphoneDTO toCellphoneDTO(Cellphone cellphone){
        return CellphoneDTO.builder()
                .id(cellphone.getId())
                .number(cellphone.getNumber())
                .areaCode(cellphone.getAreaCode())
                .build();
    }

    //----------------------------------------------- UPDATES -----------------------------------------------------------------------------

    public Client clientUpdate(ClientDTO clientDTO, Client clientEntity){
        return Client.builder()
                .name(clientDTO.getName() != null ? clientDTO.getName() : clientEntity.getName())
                .id(clientEntity.getId())
                .email(clientDTO.getEmail() != null ? clientDTO.getEmail() : clientEntity.getEmail())
                .password(clientDTO.getPassword() != null ? clientDTO.getPassword() : clientEntity.getPassword())
                .cellphones(clientEntity.getCellphones())
                .build();
    }

    public Cellphone cellphoneUpdate(CellphoneDTO cellphoneDTO, Cellphone cellphoneEntity){
        return Cellphone.builder()
                .id(cellphoneEntity.getId())
                .areaCode((cellphoneDTO.getAreaCode() != null ? cellphoneDTO.getAreaCode() : cellphoneEntity.getAreaCode()))
                .number(cellphoneDTO.getNumber() != null ? cellphoneDTO.getNumber() : cellphoneEntity.getNumber())
                .client_id(cellphoneEntity.getClient_id())
                .build();
    }

    //---------------------------------------------------------------------------------------------------------------------------------------

    public Cellphone toCellphone (CellphoneDTO cellphoneDTO, Long clientId){
        return Cellphone.builder()
                .areaCode(cellphoneDTO.getAreaCode())
                .number(cellphoneDTO.getNumber())
                .client_id(clientId)
                .build();
    }

}
