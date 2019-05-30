package com.assessment.data;
import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.assessment.Exceptions.AssessmentGenericException;
/**
 * Primary key - questionText & companyId
 * @author jsutaria
 *
 */
@Entity
public class Question extends Base{
	
	
	/**
	 * This is the identity right now. Think of some thing else
	 */
	
	@Column(length=2000)
	String questionText;
	
	//@OneToMany(targetEntity=Options.class, mappedBy="Question", fetch=FetchType.EAGER)
//	@ExcelCell(1)   
	@Lob
	String choice1;
	
//	@ExcelCell(2)   
	@Lob
	String choice2;
	
//	@ExcelCell(3)   
	@Lob
	String choice3;
	
//	@ExcelCell(4)   
	@Lob
	String choice4;
	
//	@ExcelCell(5)   
	@Lob
	String choice5;
	
//	@ExcelCell(6)   
	@Lob
	String choice6;
	
	@Transient
	Boolean one = false;
	@Transient
	Boolean two = false;
	@Transient
	Boolean three = false;
	@Transient
	Boolean four = false;
	@Transient
	Boolean five = false;
	@Transient
	Boolean six = false;
	
	//Possible value - choice2-choice4
	//@ExcelCell(7)   
	@Column(length=200)
	String rightChoices = "";
	
//	@ExcelCell(8)   
	private String qualifier1;
	
	//@ExcelCell(9)   
	private String qualifier2;
	
//@ExcelCell(10)   
	private String qualifier3;
	
	//@ExcelCell(11)   
	private String qualifier4;
	
	//@ExcelCell(12)   
	private String qualifier5;
	
	//@ExcelCell(13)   
	@Transient
	private String diff;
	
	@Enumerated(EnumType.STRING)
	private DifficultyLevel difficultyLevel = DifficultyLevel.EASY;
	
	@Enumerated(EnumType.STRING)
	private QuestionType questionType = QuestionType.MCQ;
	
	@Enumerated(EnumType.STRING)
	private ProgrammingLanguage language = ProgrammingLanguage.JAVA;
	
	@Enumerated(EnumType.STRING)
	private FullStackOptions fullstack = FullStackOptions.NONE;
	
	@Transient
	String lang;
	
	@Transient
	String stack;
	
	@Transient
	private String type;
	
	@Column(length=2000)
	private String constrnt;
	
	@Column(length=2000)
	private String stackProblemDetails;
	
	
	
	@Transient
	private String level;
	
	@Column(length=2000)
	private String inputCode;
	
	@Column(length=2000)
	private String hiddenInputPositive;
	
	@Column(length=2000)
	private String hiddenInputNegative;
	
	@Column(length=2000)
	private String hiddenOutputPositive;
	
	@Column(length=2000)
	private String hiddenOutputNegative;
	
	@Column(length=2000) 
	String instructionsIfAny;
	
	@Transient
	String category;
	
	@Transient
	String testCategory;
	
	@Transient
	String updatedDate;
	
	/**
	 * Remove from here. this is related to ui.
	 */
	@Transient
	Boolean selected;
	
	
	
	@Column(length=2000)
	String imageUrl;
	
	@Column(length=2000)
	String videoURL;
	
	@Column(length=2000)
	String audioURL;
	
	String reviewerEmail;
	
	public String getQualifier1() {
		return qualifier1;
	}
	public void setQualifier1(String qualifier1) {
		this.qualifier1 = qualifier1;
	}
	public String getQualifier2() {
		return qualifier2;
	}
	public void setQualifier2(String qualifier2) {
		this.qualifier2 = qualifier2;
	}
	public String getQualifier3() {
		return qualifier3;
	}
	public void setQualifier3(String qualifier3) {
		this.qualifier3 = qualifier3;
	}
	public String getQualifier4() {
		return qualifier4;
	}
	public void setQualifier4(String qualifier4) {
		this.qualifier4 = qualifier4;
	}
	public String getQualifier5() {
		return qualifier5;
	}
	public void setQualifier5(String qualifier5) {
		this.qualifier5 = qualifier5;
	}
	public DifficultyLevel getDifficultyLevel() {
		return difficultyLevel;
	}
	public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	
	
	
	public String getChoice1() {
		return choice1;
	}
	public void setChoice1(String choice1) {
		this.choice1 = choice1;
	}
	public String getChoice2() {
		return choice2;
	}
	public void setChoice2(String choice2) {
		this.choice2 = choice2;
	}
	public String getChoice3() {
		return choice3;
	}
	public void setChoice3(String choice3) {
		this.choice3 = choice3;
	}
	public String getChoice4() {
		return choice4;
	}
	public void setChoice4(String choice4) {
		this.choice4 = choice4;
	}
	public String getChoice5() {
		return choice5;
	}
	public void setChoice5(String choice5) {
		this.choice5 = choice5;
	}
	public String getChoice6() {
		return choice6;
	}
	public void setChoice6(String choice6) {
		this.choice6 = choice6;
	}
	public String getRightChoices() {
		return rightChoices;
	}
	public void setRightChoices(String rightChoices) {
		this.rightChoices = rightChoices;
		if(this.rightChoices.equals("Choice 1")) {
			setOne(true);
		}
		
		if(this.rightChoices.equals("Choice 2")) {
			setTwo(true);
		}
		
		if(this.rightChoices.equals("Choice 3")) {
			setThree(true);
		}
		
		if(this.rightChoices.equals("Choice 4")) {
			setFour(true);
		}
		
		if(this.rightChoices.equals("Choice 5")) {
			setFive(true);
		}
		
		if(this.rightChoices.equals("Choice 6")) {
			setSix(true);
		}
	}
	public String getInstructionsIfAny() {
		return instructionsIfAny;
	}
	public void setInstructionsIfAny(String instructionsIfAny) {
		this.instructionsIfAny = instructionsIfAny;
	}
	public String getDiff() {
		return diff;
	}
	public void setDiff(String diff) {
		this.diff = diff;
		setDifficultyLevel(DifficultyLevel.valueOf(diff));
	}
	
	/*
	 * For user session report onlu
	 */
	@Transient
	public String getQuestionCategory(){
		if(getQualifier1() != null && getQualifier1().trim().length() != 0) {
			if(getQualifier2() != null && getQualifier2().trim().length() != 0) {
				if(getQualifier3() != null && getQualifier3().trim().length() != 0) {
					if(getQualifier4() != null && getQualifier4().trim().length() != 0) {
						if(getQualifier5() != null && getQualifier5().trim().length() != 0) {
							return getQualifier1()+" / "+getQualifier2() +"/"+getQualifier3()+"/"+getQualifier4()+"/"+getQualifier5();
						}
						else {
							return getQualifier1()+" / "+getQualifier2() +"/"+getQualifier3()+"/"+getQualifier4();
						}
					}
					else {
						return getQualifier1()+" / "+getQualifier2() +"/"+getQualifier3();
					}
				}
				else {
					return getQualifier1()+" / "+getQualifier2();
				}
			}
			else {
				return getQualifier1();
			}
		}
		return "";
	}
	
	
	public String getCategory() {
		if(getQualifier1() != null && getQualifier1().trim().length() != 0) {
			if(getQualifier2() != null && getQualifier2().trim().length() != 0) {
				if(getQualifier3() != null && getQualifier3().trim().length() != 0) {
					if(getQualifier4() != null && getQualifier4().trim().length() != 0) {
						if(getQualifier5() != null && getQualifier5().trim().length() != 0) {
							return getHrefLevel1()+" / "+getHrefLevel2() +"/"+getHrefLevel3()+"/"+getHrefLevel4()+"/"+getHrefLevel5();
						}
						else {
							return getHrefLevel1()+" / "+getHrefLevel2() +"/"+getHrefLevel3()+"/"+getHrefLevel4();
						}
					}
					else {
						return getHrefLevel1()+" / "+getHrefLevel2() +"/"+getHrefLevel3();
					}
				}
				else {
					return getHrefLevel1()+" / "+getHrefLevel2();
				}
			}
			else {
				return getHrefLevel1();
			}
		}
		return "";
	}
	
	private String getHrefLevel1() {
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQualifier1?qualifier1="+getQualifier1();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier1());
		return url;
	}
	
	private String getHrefLevel2() {
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQualifier1And2?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier2());
		return url;
	}
	
	private String getHrefLevel3() {
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQualifier1And2And3?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2()+"&qualifier3="+getQualifier3();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier3());
		return url;
	}
	
	private String getHrefLevel4() {
		//searchQByQualifier1And2And3
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQualifier1And2And3And4?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2()+"&qualifier3="+getQualifier3()+"&qualifier4="+getQualifier4();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier4());
		return url;
	}
	
	private String getHrefLevel5() {
		//searchQByQualifier1And2And3
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQualifier1And2And3And4And5?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2()+"&qualifier3="+getQualifier3()+"&qualifier4="+getQualifier4()+"&qualifier5="+getQualifier5();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier5());
		return url;
	}
	
	private String getTestHrefLevel1() {
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQ1?qualifier1="+getQualifier1();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier1());
		return url;
	}
	
	private String getTestHrefLevel2() {
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQ1And2?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier2());
		return url;
	}
	
	private String getTestHrefLevel3() {
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQ1And2And3?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2()+"&qualifier3="+getQualifier3();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier3());
		return url;
	}
	
	private String getTestHrefLevel4() {
		//searchQByQualifier1And2And3
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQ1And2And3And4?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2()+"&qualifier3="+getQualifier3()+"&qualifier4="+getQualifier4();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier4());
		return url;
	}
	
	private String getTestHrefLevel5() {
		//searchQByQualifier1And2And3
		String url = "<a href=\"$url$\">$cat$</a>";
		String meth = "searchQByQ1And2And3And4And5?qualifier1="+getQualifier1()+"&qualifier2="+getQualifier2()+"&qualifier3="+getQualifier3()+"&qualifier4="+getQualifier4()+"&qualifier5="+getQualifier5();
		url = url.replace("$url$", meth);
		url = url.replace("$cat$", getQualifier5());
		return url;
	}
	
	public String getUpdatedDate() {
		String pattern = "dd-MMM-yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		if(getUpdateDate() == null) {
			if(getCreateDate() == null) {
				return "Not Available";
			}
			else {
				return simpleDateFormat.format(getCreateDate());
			}
			
		}
		else {
			return simpleDateFormat.format(getUpdateDate());
		}
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public void setUpdatedDate(String updatedDate) {
		updatedDate = updatedDate;
	}
	public Boolean getOne() {
			return this.one;
		
	}
	public void setOne(Boolean one) {
		this.one = one;
	}
	public Boolean getTwo() {
		return this.two;
	}
	public void setTwo(Boolean two) {
		this.two = two;
	}
	public Boolean getThree() {
		return this.three;
	}
	public void setThree(Boolean three) {
		this.three = three;
	}
	public Boolean getFour() {
		return this.four;
	}
	public void setFour(Boolean four) {
		this.four = four;
	}
	public Boolean getFive() {
		return this.five;
	}
	public void setFive(Boolean five) {
		this.five = five;
	}
	public Boolean getSix() {
		if(getRightChoices().contains("Choice 6")) {
			this.six = true;
			return true;
		}
		else {
			this.six = false;
			return false;
		}
	}
	public void setSix(Boolean six) {
		this.six = six;
	}
	public String getLevel() {
		return getDifficultyLevel().getLevel();
		
	}
	public void setLevel(String level) {
		if(!level.equalsIgnoreCase("-")) {
			this.level = level;
			setDifficultyLevel(DifficultyLevel.valueOf(level));
		}
		
	}
	
	public void setUpFromInUpdateMode() {
		if(getRightChoices().contains("Choice 1")) {
			this.one = true;
			
		}
		else {
			this.one = false;
			
		}
		
		if(getRightChoices().contains("Choice 2")) {
			this.two = true;
			
		}
		else {
			this.two = false;
			
		}
		
		if(getRightChoices().contains("Choice 3")) {
			this.three = true;
			
		}
		else {
			this.three = false;
			
		}
		
		if(getRightChoices().contains("Choice 4")) {
			this.four = true;
			
		}
		else {
			this.four = false;
			
		}
		
		if(getRightChoices().contains("Choice 5")) {
			this.five = true;
			
		}
		else {
			this.five = false;
			
		}
		
		if(getRightChoices().contains("Choice 6")) {
			this.six = true;
			
		}
		else {
			this.six = false;
			
		}
	}
	
	public void setup() {
		if(getQuestionType().getType().equalsIgnoreCase(QuestionType.MCQ.getType())) {
			rightChoices = "";
			if(getOne()) {
				rightChoices = "Choice 1-";
			}
			
			if(getTwo()) {
				rightChoices+= "Choice 2-";
			}
			
			if(getThree()) {
				rightChoices+= "Choice 3-";
			}
			
			if(getFour()) {
				rightChoices+= "Choice 4-";
			}
			
			if(getFive()) {
				rightChoices+= "Choice 5-";
			}
			
			if(getSix()) {
				rightChoices+= "Choice 6-";
			}
			
			if(rightChoices.length() == 0 ) {
				throw new AssessmentGenericException("NO_CORRECT_CHOICE");
			}
			
			rightChoices = rightChoices.substring(0, rightChoices.lastIndexOf("-"));
		}
		else if(getQuestionType().getType().equalsIgnoreCase(QuestionType.FULL_STACK_JAVA.getType())){
			setFullstack(FullStackOptions.JAVA_FULLSTACK);
		}
		
	}
	
	@Override
	public int hashCode() {
		return getId().hashCode();
	}
	
	@Override
	public boolean equals(Object object) {
		if(! (object instanceof Question)) {
			return false;
		}
		
		Question dto = (Question) object;
		if(dto.hashCode() == hashCode()) {
			return true;
		}
		
		return false;
	}
	public Boolean getSelected() {
		return selected;
	}
	public void setSelected(Boolean selected) {
		this.selected = selected;
	}
	public String getTestCategory() {
		if(getQualifier1() != null && getQualifier1().trim().length() != 0) {
			if(getQualifier2() != null && getQualifier2().trim().length() != 0) {
				if(getQualifier3() != null && getQualifier3().trim().length() != 0) {
					if(getQualifier4() != null && getQualifier4().trim().length() != 0) {
						if(getQualifier5() != null && getQualifier5().trim().length() != 0) {
							return getTestHrefLevel1()+" / "+getTestHrefLevel2() +"/"+getTestHrefLevel3()+"/"+getTestHrefLevel4()+"/"+getTestHrefLevel5();
						}
						else {
							return getTestHrefLevel1()+" / "+getTestHrefLevel2() +"/"+getTestHrefLevel3()+"/"+getTestHrefLevel4();
						}
					}
					else {
						return getTestHrefLevel1()+" / "+getTestHrefLevel2() +"/"+getTestHrefLevel3();
					}
				}
				else {
					return getTestHrefLevel1()+" / "+getTestHrefLevel2();
				}
			}
			else {
				return getTestHrefLevel1();
			}
		}
		return "";
	}
	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}
	public QuestionType getQuestionType() {
		return questionType;
	}
	public void setQuestionType(QuestionType questionType) {
		this.questionType = questionType;
	}
	public String getType() {
		
		if( getQuestionType() == null) {
			return QuestionType.MCQ.getType();
		}
		return getQuestionType().getType();
	}
	public void setType(String type) {
		this.type = type;
		if(!type.equalsIgnoreCase("-")) {
			this.type = type;
			setQuestionType(QuestionType.valueOf(type));
		}
	}
	public ProgrammingLanguage getLanguage() {
		return language;
	}
	public void setLanguage(ProgrammingLanguage language) {
		this.language = language;
		this.lang = this.language != null?this.language.getLanguage():null;
	}
	public String getLang() {
		return getLanguage().getLanguage();
	}
	public void setLang(String lang) {
		this.lang = lang;
		if(!lang.equalsIgnoreCase("-")) {
			this.lang = lang;
			this.language = lang != null?(ProgrammingLanguage.valueOf(lang)):null;
		}
	}
	
	
	public String getConstrnt() {
		return constrnt;
	}
	public void setConstrnt(String constrnt) {
		this.constrnt = constrnt;
	}
	
	
	
	
	public String getInputCode() {
		return inputCode;
	}
	public void setInputCode(String inputCode) {
		this.inputCode = inputCode;
	}
	public String getHiddenInputPositive() {
		return hiddenInputPositive;
	}
	public void setHiddenInputPositive(String hiddenInputPositive) {
		this.hiddenInputPositive = hiddenInputPositive;
	}
	public String getHiddenInputNegative() {
		return hiddenInputNegative;
	}
	public void setHiddenInputNegative(String hiddenInputNegative) {
		this.hiddenInputNegative = hiddenInputNegative;
	}
	public String getHiddenOutputPositive() {
		return hiddenOutputPositive;
	}
	public void setHiddenOutputPositive(String hiddenOutputPositive) {
		this.hiddenOutputPositive = hiddenOutputPositive;
	}
	public String getHiddenOutputNegative() {
		return hiddenOutputNegative;
	}
	public void setHiddenOutputNegative(String hiddenOutputNegative) {
		this.hiddenOutputNegative = hiddenOutputNegative;
	}
	
	
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVideoURL() {
		return videoURL;
	}
	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}
	public String getAudioURL() {
		return audioURL;
	}
	public void setAudioURL(String audioURL) {
		this.audioURL = audioURL;
	}
	public FullStackOptions getFullstack() {
		if(this.fullstack == null){
			return FullStackOptions.NONE;
		}
		return fullstack;
	}
	public void setFullstack(FullStackOptions fullstack) {
		this.fullstack = fullstack;
	}
	public String getStack() {
		if(this.fullstack != null){
			return this.fullstack.getStack();
		}
		return stack;
	}
	public void setStack(String stack) {
		setFullstack(FullStackOptions.valueOf(stack));
		this.stack = stack;
	}
	public String getStackProblemDetails() {
		return stackProblemDetails;
	}
	public void setStackProblemDetails(String stackProblemDetails) {
		this.stackProblemDetails = stackProblemDetails;
	}
	public String getReviewerEmail() {
		return reviewerEmail;
	}
	public void setReviewerEmail(String reviewerEmail) {
		this.reviewerEmail = reviewerEmail;
	}
	
	
}
