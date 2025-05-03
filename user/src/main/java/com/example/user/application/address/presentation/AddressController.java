package com.example.user.application.address.presentation;

import com.example.user.application.address.presentation.dto.req.AddressInput;
import com.example.user.application.address.presentation.dto.req.CreateAddressInput;
import com.example.user.application.address.presentation.dto.req.DeleteAddressInput;
import com.example.user.application.address.presentation.dto.req.UpdateAddressInput;
import com.example.user.application.address.presentation.dto.res.AddressResponse;
import com.example.user.application.address.service.MutationAddressService;
import com.example.user.application.address.service.QueryAddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class AddressController {

    private final MutationAddressService mutationAddressService;
    private final QueryAddressService queryAddressService;

    @QueryMapping
    public AddressResponse getAddressByAddressId(
            @Argument(name = "addressInput") AddressInput addressInput
    ){
        return queryAddressService.getAddressByAddressId(addressInput);
    }

    @QueryMapping
    public List<AddressResponse> getAddressesByUserId(){
        return queryAddressService.getAddressesByUserId();
    }

    @MutationMapping
    public AddressResponse createAddress(
            @Argument(name = "createAddressInput") CreateAddressInput createAddressInput
    ){
        return mutationAddressService.createAddress(createAddressInput);
    }

    @MutationMapping
    public AddressResponse updateAddress(
            @Argument(name = "updateAddressInput") UpdateAddressInput updateAddressInput
    ){
        return mutationAddressService.updateAddress(updateAddressInput);
    }

    @MutationMapping
    public AddressResponse deleteAddress(
            @Argument(name = "deleteAddressInput") DeleteAddressInput deleteAddressInput
    ){
        return mutationAddressService.deleteAddress(deleteAddressInput);
    }

}
