package br.com.testealelo.aleloapi;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(JUnit4.class)
@SpringBootTest(classes = AleloApiApplication.class)
class Testing {

    @Test
    public void testextmethod1() {
    
      System.out.println("test ext method 1");
    
    }
    
    @Nested
    class TestNest{
    
       @BeforeEach
       public void init() {
          System.out.println("Init");
       }
    
       @Test
       public void testmethod1() {
          System.out.println("This is method 1");
       }
    
       @Test
       public void testmethod2() {
          System.out.println("This is method 2");
       }
    
       @Test
       public void testmethod3() {
          System.out.println("This is method 3");
       }
        
     }

     @Test
     public void testextmethod2() {
    
         System.out.println("test ext method 2");
    
     }

}