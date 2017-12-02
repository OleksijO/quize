import React from 'react';

export default class Subject extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            subject: props.subject
        };
        this.setValue = this.setValue.bind(this);
        this.deleteEntry = this.deleteEntry.bind(this);
    }

    setValue(field, event) {
        let object = {};
        object[field] = event.target.value;
        object['index'] = this.props.subject.index;
        this.props.updateState(object);
    }

    deleteEntry() {
        this.props.deleteEntry(this.props.subject);
    }

    render() {
        return (
            <div>
                <input type="text" name="name" id="name" className="form-control input-sm"
                       value={this.state.subject.name}
                       onChange={this.setValue.bind(this, 'name')}/>
                <input type="button" value="Delete" className="btn btn-info btn-block"
                       onClick={this.deleteEntry}/>

                <br/>
            </div>

        );
    }
}
