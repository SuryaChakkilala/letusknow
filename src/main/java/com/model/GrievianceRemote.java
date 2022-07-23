package com.model;

import java.util.List;

import javax.ejb.Remote;

import com.entity.Grieviance;

@Remote
public interface GrievianceRemote {
	public String insertGrieviance(Grieviance g) throws Exception;
	public List<Grieviance> getGrieviances() throws Exception;
	public String removeGrieviance(int id) throws Exception;
}
