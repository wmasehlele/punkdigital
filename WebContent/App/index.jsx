import React from 'react';
import {render} from 'react-dom';

class App extends React.Component {
    constructor(props) {
      super(props);
      this.state = {
        names: '',
        position: '',
        address: '',
        tell: '',
        cell: '',
        website: '',
        companyname: '',
      };
      this.setNames = this.setNames.bind(this);
      this.setPosition = this.setPosition.bind(this);
      this.setAddress = this.setAddress.bind(this);
      this.setTell = this.setTell.bind(this);
      this.setCell = this.setCell.bind(this);
      this.setWebsite = this.setWebsite.bind(this);
      this.setCompanyname = this.setCompanyname.bind(this);
      
      this.handleSubmit = this.handleSubmit.bind(this);
    }

    setNames(event) {
      this.setState({names: event.target.value});
    }
    setPosition(event) {
      this.setState({position: event.target.value});
    }
    setAddress(event) {
        this.setState({address: event.target.value});
    }    
    setTell(event) {
        this.setState({tell: event.target.value});
    }    
    setCell(event) {
        this.setState({cell: event.target.value});
    }    
    setWebsite(event) {
        this.setState({website: event.target.value});
    }    
    setCompanyname(event) {
        this.setState({companyname: event.target.value});
    }    
    handleSubmit(event) {
      var postdata = {};
      postdata.names = this.state.names;
      postdata.position = this.state.position;
      postdata.address = this.state.address;
      postdata.tell = this.state.tell;
      postdata.cell = this.state.cell;
      postdata.website = this.state.website;
      postdata.companyname = this.state.companyname;
      
      if (postdata.names == "" || postdata.position == "" || postdata.address == "" || 
          postdata.tell == "" || postdata.cell == "" || postdata.website == "" || postdata.companyname == ""){
          alert('Fill in reqired fields');
          return;
      }
      alert('A name was submitted: ' + postdata.names+ ' ' + postdata.position);
      //console.log(this.state);
      event.preventDefault();
    }

    render() {
      return (
        <form onSubmit={this.handleSubmit}>
          <table>
              <thead>
                  <tr><td><strong>Create email signature</strong></td></tr>
              </thead>
              <tbody>
                  <tr>
                      <td><label>Full names *:</label></td>
                      <td><input type="text" value={this.state.names} onChange={this.setNames} /></td>
                  </tr>
                  <tr>
                      <td><label>Potision *:</label></td>                
                      <td><input type="text" value={this.state.position} onChange={this.setPosition} /></td>
                  </tr>
                  <tr>
                      <td><label>Address *:</label></td>                
                      <td><input type="text" value={this.state.address} onChange={this.setAddress} /></td>
                  </tr> 
                  <tr>
                      <td><label>Tell *:</label></td>                
                      <td><input type="text" value={this.state.tell} onChange={this.setTell} /></td>
                  </tr> 
                  <tr>
                      <td><label>Cell *:</label></td>                
                      <td><input type="text" value={this.state.cell} onChange={this.setCell} /></td>
                  </tr>
                  <tr>
                      <td><label>Website *:</label></td>                
                      <td><input type="text" value={this.state.website} onChange={this.setWebsite} /></td>
                  </tr>
                  <tr>
                      <td><label>Company name *:</label></td>                
                      <td><input type="text" value={this.state.companyname} onChange={this.setCompanyname} /></td>
                  </tr>                      
                  <tr>
                      <td></td>
                      <td><input type="submit" value="Submit" /></td>
                  </tr>
              </tbody>
          </table>
        </form>
      );
    }
}
render(<App/>, document.getElementById('app'));