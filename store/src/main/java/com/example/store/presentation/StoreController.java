package com.example.store.presentation;

import com.example.store.domain.Store;
import com.example.store.presentation.dto.req.StoreInput;
import com.example.store.service.MutationStoreService;
import com.example.store.service.QueryStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class StoreController {

    private final MutationStoreService mutationStoreService;
    private final QueryStoreService queryStoreService;

    @MutationMapping
    public Store createStore(
            @Argument(name = "storeInput")
            StoreInput storeInput
    ) {
        return mutationStoreService.create(storeInput);
    }

    @MutationMapping
    public Store updateStore(
            @Argument(name = "storeInput")
            StoreInput storeInput
    ) {
        return mutationStoreService.update(storeInput);
    }

    @MutationMapping
    public Store deleteStore() {
        return mutationStoreService.delete();
    }

    @QueryMapping
    public Store getStore(
            @Argument(name = "storeInput")
            StoreInput storeInput
    ) {
        return queryStoreService.getStore(storeInput);
    }

    @QueryMapping
    public Store getStoreByUser(
            @Argument(name = "storeInput")
            StoreInput storeInput
    ) {
        return queryStoreService.getStoreByUser(storeInput);
    }

    @QueryMapping
    public List<Store> getStoresByName(
            @Argument(name = "storeInput")
            StoreInput storeInput
    ) {
        return queryStoreService.getStoreByName(storeInput);
    }

}
