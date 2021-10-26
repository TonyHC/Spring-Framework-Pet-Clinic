package com.springframework.springpetclinic.controller;

import com.springframework.springpetclinic.model.Owner;
import com.springframework.springpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void findOwnersForm() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processFindFormReturnNoOwner() throws Exception {
        // Given
        List<Owner> owners = new ArrayList<>();

        // When
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        // Then
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/findOwners"));
    }

    @Test
    void processFindFormReturnOneOwner() throws Exception {
        // Given
        List<Owner> owners = new ArrayList<>();
        owners.add(Owner.builder().id(1L).build());

        // When
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        // Then
        mockMvc.perform(get("/owners"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"));
    }

    @Test
    void processFindFormReturnManyOwners() throws Exception {
        // Given
        List<Owner> owners = new ArrayList<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        // When
        when(ownerService.findAllByLastNameLike(anyString())).thenReturn(owners);

        // Then
        mockMvc.perform(get("/owners"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/ownersList"))
                .andExpect(model().attribute("listOwners", hasSize(2)));
    }

    @Test
    void showOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/ownerDetails"))
                .andExpect(model().attribute("owner", hasProperty("id", is(1L))));
    }

    @Test
    void ownerCreationForm() throws Exception {
        mockMvc.perform(get("/owners/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));

        verifyNoInteractions(ownerService);
    }

    @Test
    void processOwnerCreationForm() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(post("/owners/new"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/1"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).save(any());
    }

    @Test
    void ownerUpdateForm() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1L).build());

        mockMvc.perform(get("/owners/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("/owners/createOrUpdateOwnerForm"))
                .andExpect(model().attributeExists("owner"));
    }

    @Test
    void processOwnerUpdateForm() throws Exception {
        when(ownerService.save(any())).thenReturn(Owner.builder().id(3L).build());

        mockMvc.perform(post("/owners/1/edit"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/owners/3"))
                .andExpect(model().attributeExists("owner"));

        verify(ownerService, times(1)).save(any());
    }
}