/*class signatureForm extends React.Component {
    constractor(props){
        super(props);
        this.name = {value: ''};
        this.surname = {value: ''};
        this.idnumber = {value: ''};
        
        this.setName = this.setName.bind(this);
        this.setSurname = this.setSurname.bind(this);
        this.setIdnumber = this.setIdnumber.bind(this);
    }
    setName(event){
        this.setName({value: event.target.value});
    }
    setSurname(event){
        this.setSurname({value: event.target.value});
    }
    setIdnumber(event){
        this.setIdnumber({value: event.target.value});
    }
    handleClick(event){
        aler(this.name.value);
        event.preventDefault();
    }
    
    render(){
        return (
           <form onSubmit={this.handleClick}>
                <TextInput
                  ref="name"
                  text="Your name" 
                  onChange={this.setName}/>
           </form>
        );
    }
}*/