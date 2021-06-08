package kodlamaio.hrms.adapter.concretes;

import kodlamaio.hrms.adapter.abstracts.MernisVerificationService;

public class MernisVerificationManager implements MernisVerificationService {
	
	@Override
	public boolean isVerify(String nationalIdentity) {
		
		if (nationalIdentity.length() == 11) {
			
			System.out.println("Kimlik Numaranız 11 Haneden Oluşmalıdır.");
			return false;
			
		}
		else {
			
			System.out.println("Kimlik Numarası Başarıyla Doğrulandı.");
			return true;
		}
		
		
	}
	

}
