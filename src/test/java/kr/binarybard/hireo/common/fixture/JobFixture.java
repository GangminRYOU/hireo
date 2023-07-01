package kr.binarybard.hireo.common.fixture;

import kr.binarybard.hireo.web.job.domain.Category;
import kr.binarybard.hireo.web.job.domain.Job;
import kr.binarybard.hireo.web.job.domain.JobType;
import kr.binarybard.hireo.web.job.dto.JobResponse;

import java.time.LocalDateTime;

public class JobFixture {

	private static final String SOFTWARE_ENGINEER_NAME = "Software Engineer";
	private static final String DATA_SCIENTIST_NAME = "Data Scientist";
	private static final String MARKETING_MANAGER_NAME = "Marketing Manager";
	private static final String GRAPHIC_DESIGNER_NAME = "Graphic Designer";
	private static final String EDUCATION_TRAINER_NAME = "Education & Training";

	private static final String SOFTWARE_ENGINEER_DESCRIPTION = "Develop and maintain software applications";
	private static final String DATA_SCIENTIST_DESCRIPTION = "Analyze and interpret complex datasets";
	private static final String MARKETING_MANAGER_DESCRIPTION = "Plan and execute marketing strategies";
	private static final String GRAPHIC_DESIGNER_DESCRIPTION = "Design visual content for various media";
	private static final String EDUCATION_TRAINER_DESCRIPTION = "Deliver educational programs and workshops";

	public static JobResponse createSoftwareEngineerJobResponse() {
		return JobResponse.builder()
			.id(1L)
			.name(SOFTWARE_ENGINEER_NAME)
			.description(SOFTWARE_ENGINEER_DESCRIPTION)
			.jobType(JobType.FULLTIME)
			.startSalary(5000)
			.endSalary(10000)
			.postedAt(LocalDateTime.now())
			.category(Category.WEB_SOFT)
			.company(CompanyFixture.createTestCompanyAResponse())
			.build();
	}

	public static JobResponse createDataScientistJobResponse() {
		return JobResponse.builder()
			.id(2L)
			.name(DATA_SCIENTIST_NAME)
			.description(DATA_SCIENTIST_DESCRIPTION)
			.jobType(JobType.FULLTIME)
			.startSalary(6000)
			.endSalary(12000)
			.postedAt(LocalDateTime.now())
			.category(Category.DATA_SCI)
			.company(CompanyFixture.createTestCompanyBResponse())
			.build();
	}

	public static JobResponse createMarketingManagerJobResponse() {
		return JobResponse.builder()
			.id(3L)
			.name(MARKETING_MANAGER_NAME)
			.description(MARKETING_MANAGER_DESCRIPTION)
			.jobType(JobType.PARTTIME)
			.startSalary(4000)
			.endSalary(8000)
			.postedAt(LocalDateTime.now())
			.category(Category.SALE_MAR)
			.company(CompanyFixture.createTestCompanyBResponse())
			.build();
	}

	public static JobResponse createGraphicDesignerJobResponse() {
		return JobResponse.builder()
			.id(4L)
			.name(GRAPHIC_DESIGNER_NAME)
			.description(GRAPHIC_DESIGNER_DESCRIPTION)
			.jobType(JobType.PARTTIME)
			.startSalary(3000)
			.endSalary(6000)
			.postedAt(LocalDateTime.now())
			.category(Category.GRAP_DES)
			.company(CompanyFixture.createTestCompanyAResponse())
			.build();
	}

	public static JobResponse createEducationTrainerJobResponse() {
		return JobResponse.builder()
			.id(5L)
			.name(EDUCATION_TRAINER_NAME)
			.description(EDUCATION_TRAINER_DESCRIPTION)
			.jobType(JobType.INTERNSHIP)
			.startSalary(2000)
			.endSalary(4000)
			.postedAt(LocalDateTime.now())
			.category(Category.EDU_TRAI)
			.company(CompanyFixture.createTestCompanyBResponse())
			.build();
	}

	public static Job createSoftwareEngineerJob() {
		return Job.builder()
			.name(SOFTWARE_ENGINEER_NAME)
			.jobType(JobType.FULLTIME)
			.startSalary(5000)
			.endSalary(10000)
			.description(SOFTWARE_ENGINEER_DESCRIPTION)
			.category(Category.WEB_SOFT)
			.company(CompanyFixture.createTestCompanyA())
			.build();
	}

	public static Job createDataScientistJob() {
		return Job.builder()
			.name(DATA_SCIENTIST_NAME)
			.jobType(JobType.FULLTIME)
			.startSalary(6000)
			.endSalary(12000)
			.description(DATA_SCIENTIST_DESCRIPTION)
			.category(Category.DATA_SCI)
			.company(CompanyFixture.createTestCompanyB())
			.build();
	}

	public static Job createMarketingManagerJob() {
		return Job.builder()
			.name(MARKETING_MANAGER_NAME)
			.jobType(JobType.PARTTIME)
			.startSalary(4000)
			.endSalary(8000)
			.description(MARKETING_MANAGER_DESCRIPTION)
			.category(Category.SALE_MAR)
			.company(CompanyFixture.createTestCompanyB())
			.build();
	}

	public static Job createGraphicDesignerJob() {
		return Job.builder()
			.name(GRAPHIC_DESIGNER_NAME)
			.jobType(JobType.PARTTIME)
			.startSalary(3000)
			.endSalary(6000)
			.description(GRAPHIC_DESIGNER_DESCRIPTION)
			.category(Category.GRAP_DES)
			.company(CompanyFixture.createTestCompanyA())
			.build();
	}

	public static Job createEducationTrainerJob() {
		return Job.builder()
			.name(EDUCATION_TRAINER_NAME)
			.jobType(JobType.INTERNSHIP)
			.startSalary(2000)
			.endSalary(4000)
			.description(EDUCATION_TRAINER_DESCRIPTION)
			.category(Category.EDU_TRAI)
			.company(CompanyFixture.createTestCompanyB())
			.build();
	}
}
