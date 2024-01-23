package br.com.trosoftware.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.trosoftware.data.vo.v1.PersonVO;
import br.com.trosoftware.data.vo.v2.PersonVOV2;
import br.com.trosoftware.service.PersonService;
import br.com.trosoftware.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints para documentação swagger cadastro pessoas")
public class PersonController {
	
	@Autowired
	private PersonService service;
	
	@GetMapping(value = "/{id}",produces = { MediaType.APPLICATION_JSON,  MediaType.APPLICATION_YML, 
			MediaType.APPLICATION_XML, MediaType.APPLICATION_YML })
	@Operation(summary = "Busca uma pessoa", description = "Busca cada pessoa por id", 
		tags = {"People"}, 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))),
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public PersonVO findById(
		   @PathVariable(value = "id") Long id) throws Exception {

		return service.findByid(id);
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON,  MediaType.APPLICATION_YML, 
			MediaType.APPLICATION_XML })
	@Operation(summary = "Busca todas pessoas", description = "Lista todas pessoas cadastradas em banco de dados", 
		tags = {"People"}, 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = {
							@Content(
								mediaType = "application/json",
								array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
							)
				}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public List<PersonVO> findAll() {
		return service.findAll();
	}
	
	@PostMapping(consumes = { MediaType.APPLICATION_JSON,  MediaType.APPLICATION_YML, 
			MediaType.APPLICATION_XML },
			produces = { MediaType.APPLICATION_JSON,  MediaType.APPLICATION_YML, 
					MediaType.APPLICATION_XML })
	@Operation(summary = "Cadastra pessoa", description = "Create pessoa pode ser passado dados em JSON, XML ou YML", 
		tags = {"People"}, 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public PersonVO crate(
		   @RequestBody PersonVO person) throws Exception {

		return service.create(person);
	}
	
	@PutMapping(consumes = { MediaType.APPLICATION_JSON,  MediaType.APPLICATION_YML, MediaType.APPLICATION_XML },
			produces = { MediaType.APPLICATION_JSON,  MediaType.APPLICATION_YML, MediaType.APPLICATION_XML })
	@Operation(summary = "Edita Pessoa", description = "Edita pessoa por id, utilizando JSON, XML ou YML", 
		tags = {"People"}, 
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", 
						content = @Content(schema = @Schema(implementation = PersonVO.class))),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public PersonVO update(
		   @RequestBody PersonVO person) throws Exception {

		return service.update(person);
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary = "Deleta pessoa", description = "Deleta pessoa passando Id", 
		tags = {"People"}, 
		responses = {
				@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unaunthorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
		})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	//Endpoint de consulta por ID
	@DeleteMapping(value = "/{id}/{checkId}")
	public ResponseEntity<?> deleteForId(@PathVariable(value = "id") Long id) {
			service.delete(id);
			return ResponseEntity.noContent().build();
	}
	
	
}
