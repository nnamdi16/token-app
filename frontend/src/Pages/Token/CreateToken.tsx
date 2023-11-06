import React, { useState } from "react";
import { toast } from "react-toastify";

import Input from "../../components/Input";
import styles from "./styles.module.css";
import { generateToken } from "../../services/token.service";
import Button from "../../components/Button";


const CreateToken = () => {
  const [token, setToken] = useState<string >('');
  const [generatedToken, setGeneratedToken] = useState<string | undefined>(undefined);
  const [isLoading, setIsLoading] = useState(false);

  const handleOnchange = (event?: React.ChangeEvent<HTMLInputElement>) => {

    event?.preventDefault();
    // Use a regular expression to remove non-numeric characters
    const numericValue = event?.target.value.replace(/[^0-9]/g, '');
    setToken(numericValue!!);
  };
  const handleSubmit = async (event: React.FormEvent) => {
    event.preventDefault();

    if (!token) {
      return toast.error('Enter a valid token');
    }
    //call end point
    try {
      setIsLoading(prev => !prev);
      const createToken = await generateToken(token.trim());
      setIsLoading(prev => !prev);
      const { data, error, message, status } = createToken || {};
      if (!error && status === 200) {
        setGeneratedToken(data);
        setToken('');
        toast.success('Token generated successfully');
        return;
      }
      setGeneratedToken('')
      message ? toast.error(message) : toast.error('An error occurred while generating the token');
      return;
    } catch (ex) {
      if (ex instanceof Error) {
        toast.error(ex.message);
      }
    }
  };

  return (
    <section className={styles.container}>
      <form onSubmit={handleSubmit} className={styles.container}>
        <h1>Create Token </h1>
        <Input value={token} onChange={handleOnchange} placeholder="Enter pin" />
        <Button isDisabled={isLoading} text="Create Token" />
      </form>
      {generatedToken && <label>Generated Token:{generatedToken}</label>}
    </section>
  );
};

export default CreateToken;
