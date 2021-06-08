package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import kodlamaio.hrms.business.abstracts.EmployersService;
import kodlamaio.hrms.business.abstracts.UsersService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.EmployersDao;
import kodlamaio.hrms.entities.concretes.Employers;

public class EmployersManager implements EmployersService {
	
	private EmployersDao employersDao;
	private UsersService usersService;
	
	@Autowired
	public EmployersManager(EmployersDao employersDao) {
		super();
		this.employersDao = employersDao;
	}
	
	
	@Override
	public DataResult<List<Employers>> getAll() {
		return new SuccessDataResult<List<Employers>>(this.employersDao.findAll(), "İşverenler Listelendi.");
	}

	@Override
	public Result employersAdd(Employers employers) {
		
		if(fieldsMustFilled(employers) == false) {
			return new ErrorResult("Alanlar Boş Bırakılamaz");
		}
		
		if(emailControl(employers) == false) {
			return new ErrorResult("E-Posta Sistemde Zaten Mevcut");
		}
		
		return new SuccessDataResult(employers.getEmail(),"Kullanıcı Kayıt Edildi.");
	}
	
	private boolean fieldsMustFilled(Employers employers) {
		
		if(employers.getCompanyName() == null || employers.getEmail() == null || employers.getPhoneNumber() == null ||
				employers.getPassword() == null || employers.getWebSite() == null) {
			
			return false;
		}
		
		return true;
	}
	
	private boolean emailControl(Employers employers) {
		
		if (usersService.getByEmail(employers.getEmail()).getData()!=null) {
			
			return false;
			
		}
		return true;
		
	}
	
	

}
