package com.fluidnotions.databases.fake;

import com.fluidnotions.graphql.generated.types.Cat;
import com.fluidnotions.graphql.generated.types.Dog;
import com.fluidnotions.graphql.generated.types.Pet;
import com.fluidnotions.graphql.generated.types.PetFoodType;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FakePetDataSource {
    private static final Logger logger = LoggerFactory.getLogger(FakePetDataSource.class);

    public static final List<Pet> PET_LIST = new ArrayList<>();

    @Autowired
    private Faker faker;

    @PostConstruct
    private void postConstruct() {
        for (int i = 0; i < 10; i++) {
            Pet animal = switch (i % 2) {
                case 0:
                    yield Dog.newBuilder().name(faker.dog().name())
                            .food(PetFoodType.OMNIVORE)
                            .breed(faker.dog().breed())
                            .size(faker.dog().size())
                            .coatLength(faker.dog().coatLength())
                            .build();
                default:
                    yield Cat.newBuilder().name(faker.cat().name())
                            .food(PetFoodType.CARNIVORE)
                            .breed(faker.cat().breed())
                            .registry(faker.cat().registry())
                            .build();
            };

            PET_LIST.add(animal);
        }
    }
}
