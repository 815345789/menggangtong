package com.PRD.dao;

import java.util.List;

import com.PRD.model.City;


public interface CityDao {
	public City selectCity(City city);
	public List<City> selectCitys(City city);
}
