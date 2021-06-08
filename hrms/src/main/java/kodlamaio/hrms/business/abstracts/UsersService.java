package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.Users;

public interface UsersService {
	
	DataResult <List<Users>> getAll();
	
	DataResult <Users> getByEmail(String email);
	
	DataResult <Users> getByIdentity(String nationalIdentity);

}
