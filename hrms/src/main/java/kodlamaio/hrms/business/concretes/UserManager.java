package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UsersService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.UsersDao;
import kodlamaio.hrms.entities.concretes.Users;

@Service
public class UserManager implements UsersService {

	private UsersDao usersDao;

	@Autowired
	public UserManager(UsersDao usersDao) {
		super();
		this.usersDao = usersDao;

	}

	@Override
	public DataResult<List<Users>> getAll() {

		return null;
	}

	@Override
	public DataResult<Users> getByEmail(String email) {

		return new SuccessDataResult<Users>(this.usersDao.findEmail(email), "Email Listelendi.");
	}

	@Override
	public DataResult<Users> getByIdentity(String nationalIdentity) {

		return new SuccessDataResult<Users>(this.usersDao.findIdentity(nationalIdentity),
				"Kimlik Numaraları Listelendi.");
	}

}
