package com.project.franchise.application.usecase;

import com.project.franchise.application.dto.request.franchise.CreateFranchiseRequest;
import com.project.franchise.application.usecase.franchise.CreateFranchiseUseCase;
import com.project.franchise.domain.model.Franchise;
import com.project.franchise.domain.repository.FranchiseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CreateFranchiseUseCaseTest {
    @Mock
    private FranchiseRepository repository;

    @InjectMocks
    private CreateFranchiseUseCase createFranchiseUseCase;

    @Test
    void shouldCreateFranchise() {
        when(repository.existsByName("Nike")).thenReturn(false);
        when(repository.save(any())).thenReturn(new Franchise(1L, "Nike"));

        var response = createFranchiseUseCase.execute(new CreateFranchiseRequest("Nike"));

        assertEquals("Nike", response.name());
    }
}
