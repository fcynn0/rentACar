package kodlama.io.rentacar.business.concretes;

import kodlama.io.rentacar.business.abstracts.ModelService;
import kodlama.io.rentacar.business.requests.CreateModelRequest;
import kodlama.io.rentacar.business.responses.GetAllBrandsResponse;
import kodlama.io.rentacar.business.responses.GetAllModelsResponse;
import kodlama.io.rentacar.core.utilities.mappers.ModelMapperService;
import kodlama.io.rentacar.dataAccess.abstracts.ModelRepository;
import kodlama.io.rentacar.entities.concretes.Brand;
import kodlama.io.rentacar.entities.concretes.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ModelManager implements ModelService {
    private ModelRepository modelRepository;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetAllModelsResponse> getAll() {
        List<Model> models=modelRepository.findAll();
        List<GetAllModelsResponse>getAllModelsResponses=models.stream()
                .map(model -> this.modelMapperService.forResponse()
                        .map(model,GetAllModelsResponse.class)).toList();

        return getAllModelsResponses;
    }

    @Override
    public void add(CreateModelRequest createModelRequest) {
        Model model=this.modelMapperService.forRequest().map(createModelRequest,Model.class);
        this.modelRepository.save(model);
    }
}
