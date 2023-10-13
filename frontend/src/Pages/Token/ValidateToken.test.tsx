import { fireEvent, render , screen, waitFor} from "@testing-library/react";
import * as  tokenApi from '../../services/token.service';
import '@testing-library/jest-dom';
import ValidateToken from "./ValidateToken";


jest.mock("../../services/token.service");
jest.useFakeTimers()
describe('Validate Token component', () => { 

    it('displays no token for a user when there is no pin input', async() => {
        render(<ValidateToken/>)
        const createTokenText = screen.getAllByText('Validate Token');
        createTokenText.forEach((element) => {
            expect(element).toBeInTheDocument();
          });

    });


    it('should generate a token', async () => {
        render(<ValidateToken />);
      
        const mockedResponse ={
            success: true,
            data: "2290-9009-2990-9099",
            error: null,
            message: "successfully generated token",
            status: 200
        } 
        const generateToken = jest
        .spyOn(tokenApi, "validateToken")
        .mockImplementation(() => Promise.resolve(mockedResponse));
        
        
        const input = screen.getByPlaceholderText('Enter Token');
        fireEvent.change(input, { target: { value: '2290-9009-2990-9099' } });
        const createTokenButton = screen.getByRole('button', { name: 'Validate Token' });
        
        fireEvent.click(createTokenButton);

      
     
        await waitFor(() => {
          expect(generateToken).toHaveBeenCalledWith('2290900929909099');
        });

      });
 })
