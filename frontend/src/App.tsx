import React, { useState } from 'react';
import { ToastContainer } from 'react-toastify';

import CreateToken from './Pages/Token/CreateToken';
import ValidateToken from './Pages/Token/ValidateToken';
import 'react-toastify/dist/ReactToastify.css';

import './App.css';

const tokenTabArr = ["Create Token", "Validate Token"] as const;
type tabs = typeof tokenTabArr[number];
function App() {
  const [activeTab, setActiveTab] = useState<tabs>("Create Token");
  return (
    <>
      <div className="App">
        {tokenTabArr.map((tab) => {
          return (
            <section
              className={tab===activeTab ? "active":"tabs"}
              key={tab}
              onClick={() => setActiveTab(tab)}
            >
              {tab}
            </section>
          );
        })}
      </div>

      <div className="body">
        {activeTab === "Create Token" && (
            <CreateToken />
        )}
        {activeTab === "Validate Token" && (
            <ValidateToken />
        )}
      </div>
      <ToastContainer/>
    </>
  );
}

export default App;
