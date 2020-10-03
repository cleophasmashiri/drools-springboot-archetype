#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.model;

public class Applicant {

	String name;
	int age;
	double salary;
	int experienceInYears;

	public String getName() {
		return name;
	}

	public Applicant(String name, int age, double salary, int experienceInYears) {
		super();
		this.name = name;
		this.age = age;
		this.salary = salary;
		this.experienceInYears = experienceInYears;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getExperienceInYears() {
		return experienceInYears;
	}

	public void setExperienceInYears(int experienceInYears) {
		this.experienceInYears = experienceInYears;
	}

}
