package interfaces;

public interface IHelperDao<DomainObject> {
		
		public  void insert(DomainObject obj);
		
		public  void update(DomainObject obj);
		
		public  void delete(DomainObject obj);
		
		public  String find(int id);
		
		public  DomainObject findModel(int id);
		
		public  String findAll();
		
	}
