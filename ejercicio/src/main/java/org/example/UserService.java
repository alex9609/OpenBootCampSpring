package org.example;

import org.springframework.stereotype.Component;

@Component
public class UserService {

    private NotationService notationService;

    public UserService(NotationService notationService){
        this.notationService = notationService;

    }

    public NotationService getNotationService() {
        return notationService;
    }
}
