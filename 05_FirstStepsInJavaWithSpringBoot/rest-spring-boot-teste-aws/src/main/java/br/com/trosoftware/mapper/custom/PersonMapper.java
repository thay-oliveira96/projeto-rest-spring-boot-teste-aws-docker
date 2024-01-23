package br.com.trosoftware.mapper.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.trosoftware.data.vo.v2.PersonVOV2;
import br.com.trosoftware.model.Person;

@Service
public class PersonMapper {

	public PersonVOV2 covertEntityToVo(Person person) {
		PersonVOV2 vo = new PersonVOV2();
		vo.setId(person.getId());
		vo.setAdress(person.getAdress());
		vo.setBirthDay(new Date());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setGender(person.getGender());
		return vo;
	}
	
	public Person convertVoTOEntity(PersonVOV2 person) {
		Person entity = new Person();
		entity.setId(person.getId());
		entity.setAdress(person.getAdress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		return entity;
	}
}
