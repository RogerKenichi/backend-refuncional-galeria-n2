package interfaces;

import java.util.ArrayList;

import org.json.JSONArray;

public interface IHelperDaoJ<DomainObject> {
		
		public  void insert(DomainObject obj);
		
		public  void update(DomainObject obj);
		
		public  void delete(DomainObject obj);
		
		public  JSONArray find(int id) throws Exception;
		
		public  ArrayList<JSONArray> findAll() throws Exception;
		
	}
