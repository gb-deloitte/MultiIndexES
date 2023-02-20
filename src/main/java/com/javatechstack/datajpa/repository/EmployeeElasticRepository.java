package com.javatechstack.datajpa.repository;




import com.javatechstack.datajpa.esindex.EmployeeIndex;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
 public interface EmployeeElasticRepository extends ElasticsearchRepository<EmployeeIndex, Integer> {
 List<EmployeeIndex> findAllByPrevExperienceGreaterThan(int experience);

 @Query("{\"bool\":{\"must\":[{\"regexp\":{\"skills.skill\":{ \"value\":\".*?0.*\", \"case_insensitive\": true }}}]}}")
 List<EmployeeIndex>  findAllBySkills(String skill);

 @Query("{\"bool\":{\"must\":[{\"regexp\":{\"skills.skill\":{ \"value\":\".*?1.*\", \"case_insensitive\": true }}},{\"range\":{\"prevExperience\":{\"gte\":\"?0\"}}}]}}")
 List<EmployeeIndex> findEmpBySkillsAndExperience(int experience,String skill);


 @Query("{\"bool\":{\"must\":[{\"regexp\":{\"certification.name\":{ \"value\":\".*?0.*\", \"case_insensitive\": true }}}]}}")
 List<EmployeeIndex> findEmpByCertification(String certification);



 @Query("{\"query_string\": {\"query\": \"?0\" }}")
 List<EmployeeIndex> getEmployeeUsingKeyword(String keyword);


}
