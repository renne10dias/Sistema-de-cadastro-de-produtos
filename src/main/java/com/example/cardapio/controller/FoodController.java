package com.example.cardapio.controller;

import com.example.cardapio.DTO.FoodRequestDTO;
import com.example.cardapio.DTO.FoodResponseDTO;
import com.example.cardapio.entity.Food;
import com.example.cardapio.repository.FoodRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    private FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        foodRepository.save(foodData);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<String> delete(@RequestParam long iduser){ // recebe os dados para salvar
        foodRepository.deleteById(iduser);
        return new ResponseEntity<String>("Usuario deletedo com sucesso", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("{clienteId}")
    public ResponseEntity<Food> buscar(@PathVariable Long clienteId){
        return foodRepository.findById(clienteId)
                .map(ResponseEntity::ok) // CÓDIGO HTTP
                .orElse(ResponseEntity.notFound().build());  // CÓDIGO HTTP

        /*Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if(cliente.isPresent()){
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build(); */
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping // mapeia a URL
    @ResponseBody // faz um retorno pra minha requisição  == descriçao da resposta
    public ResponseEntity<?> atualizar(@RequestBody @NotNull Food food){ // recebe os dados para salvar
        if (food.getId() == null){
            return new ResponseEntity<String>("id não informado para a atualização", HttpStatus.OK);
        }
        Food user = foodRepository.save(food);
        return new ResponseEntity<Food>(user, HttpStatus.OK);

    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){
        List<FoodResponseDTO> foodList = foodRepository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }
}
