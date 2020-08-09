package com.pulawskk.dburger.controllers.v1;

import com.pulawskk.dburger.api.v1.model.IngredientDto;
import com.pulawskk.dburger.api.v1.model.IngredientListDto;
import com.pulawskk.dburger.services.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class IngredientControllerTest {

    @InjectMocks
    IngredientController ingredientController;

    @Mock
    IngredientServiceImpl ingredientService;

    MockMvc mockMvc;

    IngredientDto ingredientDto1, ingredientDto2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ingredientController).build();

        ingredientDto1 = new IngredientDto();
        ingredientDto1.setId(11L);
        ingredientDto1.setName("wheat");
        ingredientDto1.setType("ROLL");

        ingredientDto2 = new IngredientDto();
        ingredientDto2.setId(22L);
        ingredientDto2.setType("VEGETABLE");
        ingredientDto2.setName("tomato");
    }

    @Test
    void shouldGetAllIngredients_whenGetRequestIsInvoked() throws Exception {
        List<IngredientDto> ingredientDtoList = new ArrayList<>();
        ingredientDtoList.add(ingredientDto1);
        ingredientDtoList.add(ingredientDto2);

        IngredientListDto ingredientListDto = new IngredientListDto(ingredientDtoList);

        when(ingredientService.findAllIngredients()).thenReturn(ingredientListDto);

        //then
        mockMvc.perform(get(getIngredientBaseUrl())
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredients", hasSize(2)));

    }

    /**
     * Helepr method to get base ingredient url with additional slash
     * @return url
     */
    private static String getIngredientBaseUrl() {
        return IngredientController.INGREDIENT_BASE_URL + "/";
    }
}
