package co.simplon.crud;

import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.simplon.crud.model.project.IProjectRepository;
import co.simplon.crud.model.project.Project;
import co.simplon.crud.utils.Populator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CrudApplicationTests {

	@Autowired
	private Populator populator;
	
	@Autowired
	private IProjectRepository projects;
	
	@Before
	public void before()
	{
		populator.clean();
		populator.demoData();
	}
	

	@After
	public void after()
	{}
	

	@Test
	public void whatever()
	{
		//populator.clean();
	}
	
	@Test
	public void projectWith3tasksThenOneFinihsed() {		
		
		Project p = projects.findByName("Back");
		//on a bien 3 taches
		assertTrue(p.getTasks().size()==3);
		p.getTasks().stream().filter(t ->t.getName().equals("Model")).findFirst().get().setFinished(true);
		p.setLastModificationDate(LocalDateTime.now());
		projects.save(p);
		//On a maintenant 1 tache finie
		assertTrue(p.getTasks().stream().filter(t -> t.isFinished()).count()==1);
	}
}

