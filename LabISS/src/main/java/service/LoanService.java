package service;

import model.Loan;
import persistence.LoanRepository;

public class LoanService {

    LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void addImprumut(Loan loan) {
        loanRepository.addImprumut(loan);
    }

    public void removeImprumut(String isbn, String email) {
        loanRepository.removeImprumut(isbn, email);
    }
}
