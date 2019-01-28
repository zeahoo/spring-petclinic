/*
 * Copyright 2012-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@RestController
class PetController {

    private final PetRepository pets;

    @Autowired
    private Environment environment;

    public PetController(PetRepository pets) {
        this.pets = pets;
    }

    @GetMapping("/pet/{petId}")
    public PetInfoBean findById(@PathVariable("petId") int petId) {
        Pet pet = this.pets.findById(petId);
        int port = Integer.parseInt(environment.getProperty("local.server.port"));
        return new PetInfoBean(pet, port);
    }

}
