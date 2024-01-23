package br.com.trosoftware.service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.trosoftware.controllers.PersonController;
import br.com.trosoftware.data.vo.v1.PersonVO;
import br.com.trosoftware.data.vo.v2.PersonVOV2;
import br.com.trosoftware.exceptions.RequiredObjectIsNullException;
import br.com.trosoftware.exceptions.ResourceNotFoundException;
import br.com.trosoftware.mapper.DozerMapper;
import br.com.trosoftware.mapper.custom.PersonMapper;
import br.com.trosoftware.model.Person;
import br.com.trosoftware.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;
	
	@Autowired
	PersonMapper mapper;
	
	private Logger logger = Logger.getLogger(PersonService.class.getName());
	
	public PersonVO findByid(Long id) throws Exception {
		logger.info("Finding one Person");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		PersonVO vo = DozerMapper.parseObject(entity, PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		return vo;
		}
	
	public List<PersonVO> findAll() { 
		logger.info("Finding all people!");
		var persons = DozerMapper.parseListObject(repository.findAll(), PersonVO.class);
		persons.stream().forEach(p -> {
			try {
				p.add(linkTo(methodOn(PersonController.class).findById(p.getKey())).withSelfRel());
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
		return persons;
	}
	
	public PersonVO create(PersonVO person) throws Exception {
		
		if(person == null) throw new RequiredObjectIsNullException();
		
		logger.info("Creating one person!");
		
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public PersonVOV2 createV2(PersonVOV2 person) {
		
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Creating one person with V2!");
		
		var entity = mapper.convertVoTOEntity(person);
		var vo = mapper.covertEntityToVo(repository.save(entity));
		return vo;
	}
	
	
	public PersonVO update(PersonVO person) throws Exception {
		
		if(person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating one person!");
		
		var entity = repository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		vo.add(linkTo(methodOn(PersonController.class).findById(vo.getKey())).withSelfRel());
		return vo;
	}
	
	public void delete(Long id) {
		logger.info("Deleting one person!");
		
		var entity = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
		repository.delete(entity);
		
	}

}
