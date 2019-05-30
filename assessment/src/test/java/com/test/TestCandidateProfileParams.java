package com.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.CandidateProfileParams;
import com.assessment.services.CandidateProfileParamsService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:appContext.xml"})
@Transactional
public class TestCandidateProfileParams {
	
	@Autowired
	CandidateProfileParamsService candidateProfileParamsService;
	
	@Test
	@Rollback(value=false)
	public void testCreateCandidateProfileParams(){
		CandidateProfileParams candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("General Knowledge");
		
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty poor. Not suitable for jobs where job profile is horizontal - little bit of everything.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not adequate. Need to get better.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Not bad!. Folks, on average, on GK front are like this test giver.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Good. Better than average. Would always have some thing to talk in most conversation's.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional.  High scorers on tests of general knowledge tend to also score highly on intelligence tests. Highly recommended.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Mathematical Reasoning");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty poor. Not suitable for Programming Jobs that requires a bit of analysis.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not adequate. But can be groomed to be better. For projects needing folks with a 'algorithmic' mind set, may be you have to look at other folks.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Not bad!. Around 70 % of folks fall in this category.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Good. Better than average. Has good potential in a Programming career. Especially better suited for writing algorithmic Code.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional.  Have a potential to be a Champion. The candidate can be a part of your core (Analysis & Design) team later on.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Vocabulary");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Beginner's Level. Not suited for public facing jobs.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Lower - Intermediate. But can be groomed to be better - however better suited at 'on the desk' jobs.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Not bad!. Around most folks fall in this category. To really make an impact in a public facing job, need to improve a bit.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Good. Better than average. Has a knack with words. Can be good at public facing Jobs.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional.  Have a distinct flair with words. Recommended for public facing Jobs.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Verbal Reasoning");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Beginner's Level. The verbal test’ s level of difficulty is determined by four main factors – the length of the reading passage, complexity of the text, use of higher language rather than simple words, and time constraints.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Lower - Intermediate. The verbal test’ s level of difficulty is determined by four main factors – the length of the reading passage, complexity of the text, use of higher language rather than simple words, and time constraints.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average. The verbal test’ s level of difficulty is determined by four main factors – the length of the reading passage, complexity of the text, use of higher language rather than simple words, and time constraints.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Good. The verbal test’ s level of difficulty is determined by four main factors – the length of the reading passage, complexity of the text, use of higher language rather than simple words, and time constraints.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional.  The verbal test’ s level of difficulty is determined by four main factors – the length of the reading passage, complexity of the text, use of higher language rather than simple words, and time constraints.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Reasoning");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty Elementary. Can have basic ability to recall or remember the information. However need to significantly improve for 'thinking' jobs.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Lower - Intermediate. Can explain basic ideas or concepts. If the test giver is a beginner, he or she is worth a look in.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average. Can apply information or concepts in a new way. Can think through the process of thinking about a problem in a logical way in order to form a conclusion or judgment with some effort.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Good. Has the ability to justify or defend a stand. Can think through the process of thinking about a problem in a logical way in order to form a conclusion or judgment.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional.  Can create a new point of view. Can be pretty quick to through the process of thinking about a problem in a logical way in order to form a conclusion or judgment.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Low Temperament");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Energy levels change from low to high and other way round in a jiffy. Highly sensitive. Low predictability. May have Withdrawal symptoms. Slow to adapt. Moody. High Distractability!.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Can be bit sensitive and reluctant to change. May prefer known surroundings and as such can hang on better than others - if thats what you are looking for.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. Can be optimistic, active and social along with bit short-tempered, fast or irritable at times as are most of us.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Has reasonably good temperament. Can be analytical, wise and quiet along with being optimistic, active and social.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional Temperament. Can be analytical, wise, optimistic, active, social, relaxed and peaceful. Good combination.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Good Temperament");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Exceptional Temperament. Can be analytical, wise, optimistic, active, social, relaxed and peaceful. Good combination.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Has reasonably good temperament. Can be analytical, wise and quiet along with being optimistic, active and social.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. Can be optimistic, active and social along with bit short-tempered, fast or irritable at times as are most of us.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Can be bit sensitive and reluctant to change. May prefer known surroundings and as such can hang on better than others - if thats what you are looking for.");
		candidateProfileParams.setMORE_THAN_NINETY("Energy levels change from low to high and other way round in a jiffy. Highly sensitive. Low predictability. May have Withdrawal symptoms. Slow to adapt. Moody. High Distractability!.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Honesty");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Can have Integrity Problems. Need a closer look.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Trust will come at a price. May need to spend a lot of time to establish trust.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. May need to spend little time to establish trust.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Honest! Such guys can be long term investment.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional - Too good to be true or too honest to be good.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Social");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty Poor Social Skills. This often leads to stress and loneliness and this may impact physical & mental health. Some folks have a strength of character to keep a focus and survive, if not flourish professionally despite poor social skills. However need a closer look to further assess these folks on this front.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Have basic Social skills. Can work as individual contributors. These are the folks who like one-on-one events arther than group participation in social activities. Every individual is different and so if the job profile doesn't warrants extensive networking and public approach, such folks should be considered.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. May not set the party on fire but can get the job done.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good Networking Skills. At ease in most jobs. Have some inherent mental strength are are usually confident.");
		candidateProfileParams.setMORE_THAN_NINETY("Exceptional - Best folks to be around and can be fun. Confident, Composed, and fun loving folks who themselves ahs the potential to set a party on fire.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
//		candidateProfileParams = new CandidateProfileParams();
//		candidateProfileParams.setCompanyId("IH");
//		candidateProfileParams.setCompanyName("IIHT");
//		candidateProfileParams.setQualifier1("Social");
//		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty Poor Social Skills");
//		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Have basic Social skills. Can work as individual contributors");
//		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks");
//		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good Networking Skills. At ease in most jobs");
//		candidateProfileParams.setMORE_THAN_NINETY("Exceptional - Best folks to be around and can be fun");
//		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Time Management");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Negligent Time Management, Can not be trusted to come on time or respect other's time.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Poor Time Management - Can be a concern while working in a team. Good managers can force a certain time discipline in these folks but if left on their own are more likely to make you wait.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. Usually are on time whether in meetings or on task deadlines - However at few times things may go wrong - don't expect them to proactively communicate in advance in such cases.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty good! Loves order and discipline in the way they work. Are proactive to justify their delay on time bound tasks or scheduled meetings in advanced.");
		candidateProfileParams.setMORE_THAN_NINETY("Extremely organized - For teams that values processes & structure, folks in this category can be remarkable assets. Are proactive to justify their delay on time bound tasks or scheduled meetings in advanced. More likely to to initate a time structure for meetings or tasks. Can be good managers.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Efficiency");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor, Can not be trusted on tasks. Not pro active enough to communicate. Can be pretty frustruating.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Being organized is not a trait the test giver thrives on. Though with good managers around, can be expected to do their bit.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. Most times would be good to what they are assigned.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty good! Result oriented. Most times would be good to what they are assigned. Few times when they miss, they will bear the brust of responsibility.");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent - Have traits to deliver results not just at individual but also at team level. Show leadership skills.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Ambition");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Least Ambitious - If they deliver decent productivity, can be the ones who may stick with you the most");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not much ambitious - However can be inspired to play their part by good managers and contribute their part to the best of their abilities.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks. A pretty measure of ambition in an individual is when he faces some challenging obstacles. If the obstacle by nature offeres a serious challenge, these folks may give up like most of us.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Pretty Ambitious - Can be a great investment");
		candidateProfileParams.setMORE_THAN_NINETY("Highly Ambitious - If  folks have the tools along with the ambition, they can go a long way.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Self Esteem");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty Low! Very defensive mindset. This can reduce the quality of life in many ways including leading to depression or anxiety.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve a bit - Would be defensive in pressure situations. Ability to confront problems may be lacking a bit but with some effort these folks can do well.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks around.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("High - Confident and self aware.");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent - Can be a 'go to' man if he or she know what they are doing.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Creativity");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Pretty Low!");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve a bit to fit in a dynamic work environment. ");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Typical to most folks");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("High - Can adjust well to a dynamic work environment");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent - Would be a fish in water in a dynamic work environment");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Java");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor basic concepts!. It is pretty important to understand basics so that its applicability to advanced contexts is better. With poor basics, you may end up writing repeated (more) code, have un-optimized designs and poor exception handling strategies.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve a lot.  Would require some training to fit in. Need a bettwen understanding on Collections, Multi-Threading, JDBC, basic Design Patterns or related core concept");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average. Can improve given time and commitment.");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good - Has a basic understanding on Core Java. Can Program");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent on basic concepts. Can pick up advanced concepts and frameworks pretty fast.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Java");
		candidateProfileParams.setQualifier2("JVM");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor concepts! Having a basic JVM knowledge is a must to understand application behaviour at run time.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve. Need to be trained to analyse and profile applications at run time for problems related to performance and scalability.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average - Not bad!, need to be groomed to understand advanced profiling techniques");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good - Has a good handle on JVM concepts and can be trusted to profile applications");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent! Good fit. Can understand and analyze heap dumps and thread stacks, profilers and debuggers since good in concepts");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Java");
		candidateProfileParams.setQualifier2("Threading");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor concepts! Basic threading concepts are a must in a Programming job irrespective of technology");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve. Can be trained on areas of concurrency, scalability and data integrtity  - all a consequence of how well your system is multi-threaded.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average - Not bad!, Can be a decent fit. Won't be difficult to understand application behaviour in following areas - concurrency, scalability and data integrtity");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good Multi Threading Concepts");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent! ");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Java");
		candidateProfileParams.setQualifier2("Spring");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor concepts! Can't work on projects with Spring as middleware" );
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve a lot. Spring is pretty vast - Core, Spring MVC, Spring JDBC, Spring Transactions, ORM, JNDI, Security, Integration & more. Need to be trained to qualify");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average - Not bad!, Spring is pretty vast - Core, Spring MVC, Spring JDBC, Spring Transactions, ORM, JNDI, Security, Integration & more. Should fit in well");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good. Spring is pretty vast - Core, Spring MVC, Spring JDBC, Spring Transactions, ORM, JNDI, Security, Integration & more. can relate to areas on Spring even if have not worked");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent! Though Spring is pretty vast - Core, Spring MVC, Spring JDBC, Spring Transactions, ORM, JNDI, Security, Integration & more, this candidate sounds like a top fit.");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Java");
		candidateProfileParams.setQualifier2("Hibernate");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor concepts! Can't work on projects with Hibernate as ORM" );
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve a bit. Hibernate as ORM is pretty critical in transactional applications. Need to be trained beforehand.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average - Not bad!, Has a basic undestanding on Hibernate Sessions, Session Factory & related stuff");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good fit");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent! Can work on projects that leverages a lot of Hibernate to improve query performance, scale better and maintain data integrity");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Design Patterns");
		//candidateProfileParams.setQualifier2("Hibernate");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor concepts! Can't translate a problem statement into an working design" );
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Need to improve a bit. Can't translate a problem statement into an acceptable design, though can make the code run");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average - Not bad!, Can solve basic, day-to-day design problems without much assistance");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good. Good thinker and analyzer. Has the potential to translate a problem statement into a good design ");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent! Good thinker and analyzer. Has the potential to translate a problem statement into a good design keeping future concerns (adaptibility, scalability, performance and so) in mind ");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Coding_Java");
		//candidateProfileParams.setQualifier2("Hibernate");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Poor Programming skills! Can't code" );
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Elementary Programming Skills.");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average Programming Skills, ");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Good Programming Skills");
		candidateProfileParams.setMORE_THAN_NINETY("Excellent Programming Skills");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
	}
	
	@Test
	@Rollback(value=false)
	public void testCreateCandidateProfileParamsTesting(){
		CandidateProfileParams candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Software Testing");
		candidateProfileParams.setQualifier2("Manual Testing");
		candidateProfileParams.setQualifier3("Testing Basic");
		
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Not advisable to hire, basics are very bad.");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not advisable to hire, basic is not even at average ");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average candidate need to check other factors. ");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Average candidate need to check other factors.");
		candidateProfileParams.setMORE_THAN_NINETY(" Awesome score basics are in place!!! ");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Software Testing");
		candidateProfileParams.setQualifier2("Manual Testing");
		candidateProfileParams.setQualifier3("Testing Advance");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Not advisable to hire, if test taken by an experienced candidate ");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not advisable to hire, below average candidate");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average candidature if an applicant is a fresher then it is recommended to hire ");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY(" Meets expectation, 60% of candidates falls in this category.");
		candidateProfileParams.setMORE_THAN_NINETY("Awesome score good candidature");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Software Testing");
		candidateProfileParams.setQualifier2("Manual Testing");
		candidateProfileParams.setQualifier3("Database Testing");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Not advisable to hire, if test taken by an experienced candidate ");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not advisable to hire, below average candidate");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average candidature if an applicant is a fresher then it is recommended to hire ");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Meets expectation, 50% of candidates falls in this category");
		candidateProfileParams.setMORE_THAN_NINETY("Awesome score good candidature ");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Software Testing");
		candidateProfileParams.setQualifier2("Manual Testing");
		candidateProfileParams.setQualifier3("Agile & API");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Not advisable to hire, if test taken by an experienced candidate");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not advisable to hire, below average candidate ");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average candidature if an applicant is a fresher then it is recommended to hire");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY("Meets expectation, 70% of candidates falls in this category");
		candidateProfileParams.setMORE_THAN_NINETY("Awesome score good candidature ");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		
		candidateProfileParams = new CandidateProfileParams();
		candidateProfileParams.setCompanyId("IH");
		candidateProfileParams.setCompanyName("IIHT");
		candidateProfileParams.setQualifier1("Software Testing");
		candidateProfileParams.setQualifier2("Manual Testing");
		candidateProfileParams.setQualifier3("Logical/Appti");
		candidateProfileParams.setLESS_THAN_TWENTY_PERCENT("Not advisable to hire, scores are very bad ");
		candidateProfileParams.setBETWEEN_TWENTY_AND_FIFTY("Not advisable to hire, below average candidate");
		candidateProfileParams.setBETWEEN_FIFTY_AND_SEVENTYFIVE("Average candidature if an applicant is a fresher then it is recommended to hire ");
		candidateProfileParams.setBETWEEN_SEVENTYFIVE_AND_NINETY(" Meets expectation, 80% of candidates falls in this category");
		candidateProfileParams.setMORE_THAN_NINETY(" Awesome score good candidature ");
		candidateProfileParamsService.saveOrUpdate(candidateProfileParams);
		


		
	}

}
