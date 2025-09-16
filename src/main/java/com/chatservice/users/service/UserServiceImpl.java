package com.chatservice.users.service;

import com.chatservice.users.DTO.UserDTO;
import com.chatservice.users.entity.BasicUserEntity;
import com.chatservice.users.events.supplier.UserServiceSupplier;
import com.chatservice.users.exception.CannotCreateResourceException;
import com.chatservice.users.exception.ErrorCode;
import com.chatservice.users.exception.PersonalIDNotFoundException;
import com.chatservice.users.repository.BasicUserRepository;
import com.chatservice.users.utils.ActionEnum;
import io.micrometer.tracing.Span;

import io.micrometer.tracing.Tracer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final BasicUserRepository repo;

    @Autowired
    public UserServiceSupplier userServiceSupplier;

//    @Autowired
//    Tracer tracer;

    public UserServiceImpl(BasicUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public UserDTO create(UserDTO dto) throws CannotCreateResourceException {
        if (dto == null){
            throw new CannotCreateResourceException(ErrorCode.CANNOT_CREATE_RESOURCE);
        }
        try {
            BasicUserEntity member = new BasicUserEntity();
            member.setFirstname(dto.getFirstname());
            member.setLastname(dto.getLastname());
            member.setEmail(dto.getEmail());
            member.setUsername(dto.getUsername());
            member.setPassword(dto.getPassword());
            BasicUserEntity savedMember = repo.save(member);
            userServiceSupplier.publishUserServiceChange(ActionEnum.CREATED, savedMember.getPersonalId());
            return dto;
        } catch (CannotCreateResourceException e){
            //  logger.info("Error Code: {}, \nDTO: {}, \nStack Trace: {}" , e.getErrorCode(), dto, e);
            throw new CannotCreateResourceException(ErrorCode.CANNOT_CREATE_RESOURCE);
        }

    }

    @Override
    public UserDTO getByPersonalId(String personalId) throws PersonalIDNotFoundException {
        Optional<BasicUserEntity> optional = repo.findByPersonalId(personalId);
//        Span newSpan = (Span) tracer.startScopedSpan("getUserDBCall");
        if (optional.isEmpty()) {
           throw new PersonalIDNotFoundException(ErrorCode.CANNOT_FIND_PID);

        }
        try {
            BasicUserEntity user = optional.get();
            logger.info("\nSuccessfully Retrieved BasicUserEntity With Perosnal ID: {}", personalId);
            return new UserDTO(user.getId(), user.getFirstname(), user.getLastname(), user.getEmail(), user.getUsername(), user.getPersonalId(), user.getPassword());

        } catch (PersonalIDNotFoundException e){
            //  logger.info("Error Code: {}, \nPersonal Id: {}, \nStack Trace: {}", e.getErrorCode(), personalId, e.getStackTrace());
            throw new PersonalIDNotFoundException(ErrorCode.CANNOT_FIND_PID);
        }
//        finally {
//            newSpan.tag("peer_service", "postgres");
//            newSpan.event("Client received");
//            newSpan.end();
//        }



    }

    @Override
    public boolean changeUsername(String username, String newUsername) {
        int trueFalse = repo.changeUsername(username, newUsername);
        if (trueFalse >= 1){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserByPersonalId(String personalId) throws PersonalIDNotFoundException{
        try {
        if (repo.deleteByPersonalId(personalId) == 1) {
            return true;
        }
            throw new PersonalIDNotFoundException(ErrorCode.CANNOT_FIND_PID);
        } catch(PersonalIDNotFoundException e) {
            throw new PersonalIDNotFoundException(ErrorCode.CANNOT_FIND_PID);
        }
    }

}
