import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
	
	
	
	 Restaurant restaurant;
	
    @BeforeEach
    public void init() {
    	LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }
  

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
   	 Restaurant restaurant1=Mockito.mock(Restaurant.class);
     Mockito.when(restaurant1.isRestaurantOpen()).thenReturn(true);
     Boolean isOpen=restaurant1.isRestaurantOpen();
     assertTrue(isOpen);
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
   	 Restaurant restaurant1=Mockito.mock(Restaurant.class);
        Mockito.when(restaurant1.isRestaurantOpen()).thenReturn(false);
        Boolean isOpen=restaurant1.isRestaurantOpen();
        assertTrue(!isOpen);
        
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    
    @Test
    public void getting_order_total_success_scenario() {
    	List<String> items= new ArrayList<String>(); 
    	items.add("Vegetable lasagne");
    	items.add("Sweet corn soup");
    	Integer ordertotal= restaurant.getOrderTotal(items);
    	assertEquals(388, ordertotal);
    }
    
    @Test
    public void getting_order_total_throws_exceptiono() {
    	List<String> items= new ArrayList<String>(); 
    	items.add("Pasta");
    	items.add("Sweet corn soup");
    	assertThrows(itemNotFoundException.class, ()->{ restaurant.getOrderTotal(items);});
    	
    }
}