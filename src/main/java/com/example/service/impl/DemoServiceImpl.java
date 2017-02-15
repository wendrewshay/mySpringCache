package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.dao.PersonRepository;
import com.example.domain.Person;
import com.example.service.DemoService;

@Service
public class DemoServiceImpl implements DemoService{

	@Autowired
	PersonRepository personRepository;
	
	@Override
	//缓存新增的或更新的数据到缓存，其中缓存的名称为people，数据的key是person的id
	@CachePut(value="people",key="#person.id")
	public Person save(Person person) {
		Person p = personRepository.save(person);
		System.out.println("为id、key为："+p.getId()+"做了缓存");
		return p;
	}

	@Override
	//从缓存people中删除key为id的数据
	@CacheEvict(value="people")
	public void remove(Long id) {
		System.out.println("删除了id、key为"+id+"的数据缓存");
		personRepository.delete(id);
	}

	@Override
	//缓存key为person的id数据到缓存people中
	@Cacheable(value="people",key="#person.id")
	public Person findOne(Person person) {
		Person p = personRepository.findOne(person.getId());
		System.out.println("为id、key为："+p.getId()+"数据做了缓存");
		return p;
	}

}
