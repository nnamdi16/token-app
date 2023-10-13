import React from "react";
import { fireEvent, render , screen, waitFor} from "@testing-library/react";
import CreateToken from "./CreateToken";
import * as  tokenApi from '../../services/token.service';
import '@testing-library/jest-dom';


jest.mock("../../services/token.service");
describe('Create Token component', () => { 

    it('should display a Create Token Text', async() => {
        render(<CreateToken/>)
        const createTokenText = screen.getAllByText('Create Token');
        createTokenText.forEach((element) => {
            expect(element).toBeInTheDocument();
          });

    });


    it('should generate a token', async () => {
        render(<CreateToken />);
        const mockedResponse = {
            data: '8677-8777-7657-7578',
            error: false,
            message: 'Token generated successfully',
            status: 200,
        }
        const generateToken = jest
        .spyOn(tokenApi, "generateToken")
        .mockImplementation(() => Promise.resolve(mockedResponse));
        
        
        const input = screen.getByPlaceholderText('Enter pin');
        fireEvent.change(input, { target: { value: '45678' } });
        const createTokenButton = screen.getByRole('button', { name: 'Create Token' });
        
        fireEvent.click(createTokenButton);
        
        await waitFor(() => {
          expect(generateToken).toHaveBeenCalledWith('45678');
        });
        
        const generatedTokenText = await screen.findByText(/Generated Token:8677-8777-7657-7578/i);

        expect(generatedTokenText).toBeInTheDocument();
      });
 })