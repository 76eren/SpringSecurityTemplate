package org.example.springsecuritytemplate.Seeder;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder {
    private final UserSeeder userSeeder;



    @EventListener
    public void seed(ContextRefreshedEvent event) {
        userSeeder.seed();
    }
}
