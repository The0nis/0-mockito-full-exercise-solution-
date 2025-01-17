package pt.up.fe.api;

import static org.mockito.Mockito.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class CartServiceTest {

  private PriceCalculator priceCalculator;

  private CartService cartService;

  @Before
  public void setUp() {
    // Step 1: Mock the PriceCalculator
    priceCalculator = mock(PriceCalculator.class);
    
    // Step 2: Create CartService and inject the mock
    cartService = new CartService(priceCalculator);
  }

  @Test
  public void testCalculateTotalPrice() {
    // Step 3: Stub the behavior of the mock
    when(priceCalculator.getPrice("item1")).thenReturn(10.0);
    when(priceCalculator.getPrice("item2")).thenReturn(15.0);
    when(priceCalculator.getPrice("item3")).thenReturn(20.0);

    // Step 4: Call the method under test
    List<String> itemIds = Arrays.asList("item1", "item2", "item3");
    double totalPrice = cartService.calculateTotalPrice(itemIds);

    // Step 5: Verify the result
    assertEquals(45.0, totalPrice, 0.001);

    // Step 6: Verify that the mock was interacted with correctly
    verify(priceCalculator).getPrice("item1");
    verify(priceCalculator).getPrice("item2");
    verify(priceCalculator).getPrice("item3");
    verify(priceCalculator, times(3)).getPrice(anyString());
  }
}
