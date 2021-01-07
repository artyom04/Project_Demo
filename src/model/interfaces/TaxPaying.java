package model.interfaces;

/**
 * {@code TaxPaying} interface, which has one function
 *
 * @author Artyom
 */
public interface TaxPaying {
    /**
     * Calculates the tax amount to pay
     *
     * @return the amount of tax
     */
    double calculateTaxAmount();
}