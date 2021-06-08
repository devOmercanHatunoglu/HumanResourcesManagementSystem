package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.adapter.abstracts.MernisVerificationService;
import kodlamaio.hrms.business.abstracts.JobSeekersService;
import kodlamaio.hrms.business.abstracts.UsersService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekersDao;
import kodlamaio.hrms.entities.concretes.JobSeekers;

@Service
public class JobSeekersManager implements JobSeekersService {

	private JobSeekersDao jobSeekersDao;
	private UsersService usersService;
	private MernisVerificationService mernisVerificationService;
	

	@Autowired
	public JobSeekersManager(JobSeekersDao jobSeekersDao, UsersService usersService,MernisVerificationService mernisVerificationService) {
		super();
		this.jobSeekersDao = jobSeekersDao;
		this.usersService = usersService;
		this.mernisVerificationService = mernisVerificationService;
	}

	@Override
	public DataResult<List<JobSeekers>> getAll() {

		return new SuccessDataResult<List<JobSeekers>>(this.jobSeekersDao.findAll(), "İş Arayanlar Listelendi");
	}

	@Override
	public Result jobSeekersAdd(JobSeekers jobSeekers) {
		
		if (emailControl(jobSeekers) == false) {
			
			return new ErrorResult("E-Posta Sistemde Zaten Mevcut");
			
		}
		
		if(fieldsMustFilled(jobSeekers) == false) {
			
			return new ErrorResult("Alanlar Boş Bırakılamaz.");
		}	
		
		if(identityControl(jobSeekers) == false) {
			
			return new ErrorResult("Kimlik Numarası Sistemde Zaten Mevcut");
		}
		
		if(mernisControl(jobSeekers) == false) {
			
			return new ErrorResult("Kimlik Numaranız 11 Haneden Oluşmalıdır");
		}
		
		return new SuccessDataResult(jobSeekers.getEmail(),"Kullanıcı Kayıt Edildi.");
		
	}
	
	private boolean fieldsMustFilled(JobSeekers jobSeekers) {
		
		if (jobSeekers.getFirstName() == null || jobSeekers.getLastName() == null || jobSeekers.getEmail() == null
				|| jobSeekers.getPassword() == null || jobSeekers.getNationalIdentity() == null
				|| jobSeekers.getDateOfBirth() == null) {

			return false;

		}
		return true;
		
	}
	
	private boolean emailControl(JobSeekers jobSeekers) {
		
		if (usersService.getByEmail(jobSeekers.getEmail()).getData()!=null) {
			
			return false;
			
		}
		return true;
		
	}
	
	private boolean identityControl(JobSeekers jobSeekers) {
		
		if (usersService.getByIdentity(jobSeekers.getNationalIdentity()).getData()!=null) {
			
			return false;
		}
		return true;
		
	}
	
	private boolean mernisControl(JobSeekers jobSeekers) {
		
		if(mernisVerificationService.isVerify(jobSeekers.getNationalIdentity())) {
			
			return false;
	}
		return true;
	
	}
	
	


}	
	

	
	


