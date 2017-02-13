import React from "react";
import { render } from "react-dom";

class App extends React.Component {
    render(){
        return(
           <div className="container">
                <div className="row">
                    <div className="col-lg-4 col-lg-offset-4">
                        <h4><u> Create email signature without hustle! </u></h4>
                        <br/>
                    </div>
                </div>                
                <div className="row">
                    <div className="col-lg-4 col-lg-offset-4">
                       <form>
                            <div className="form-group">
                                <label>Names: </label>
                                <input type="text" id="names" className="form-control" />
                            </div>
                            <div className="form-group">
                                <label>Position: </label>
                                <input type="text" id="position" className="form-control" />
                            </div>                                    
                            <div className="form-group">
                                <label>Tell: </label>
                                <input type="text" id="tell" className="form-control" />
                            </div>
                            <div className="form-group">
                                <label>Email: </label>
                                <input type="text" id="email" className="form-control" />
                            </div>
                            <div className="form-group" > 
                                <label>Company name: </label>
                                <input type="text" id="logoUrl" className="form-control" />
                            </div>
                            <div className="form-group">
                                <input type="button" id="generate" className="btn btn-md btn-primary" value="Generate" />
                            </div>                                     
                       </form>
                    </div>
                    <div className="col-lg-4 col-lg-offset-4">
                         <form method="GET" action="SignatureServlet">
                             <div className="form-group">
                                 <input type="submit" className="btn btn-md btn-success" value="Download" />
                             </div>  
                         </form>
                    </div>
                </div>
           </div>
        );
    }
}
render(<App/>, document.getElementById('app'));