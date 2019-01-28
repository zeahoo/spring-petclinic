package org.springframework.samples.petclinic.pet;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pet-service")
@RibbonClient(name = "pet-service")
public interface PetInfoServiceProxy {
    @GetMapping("/pet/{id}")
    PetInfoBean retrievePet(@PathVariable("id") String id);
}
