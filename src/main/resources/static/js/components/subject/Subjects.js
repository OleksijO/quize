import React from 'react';
import crudFetch from 'crud-fetch';

import Routes from '../navigation/Routes';
import Subject from "./Subject";

const URI = "/api/" + Routes.SUBJECTS;
const URI_ALL = "/api/" + Routes.SUBJECTS + "/all";

function submitForm(form) {
    form.setState({errorMessage: ''});

    let submitDto = form.state.subjects;

    crudFetch.post(URI_ALL, submitDto)
        .then((data) => {
            setIndexes(data);
            form.setState({
                subjects: data,
                errorMessage: "Subjects saved"
            });
        })
        .catch((error) => form.setState({errorMessage: error.toString()}));
}

function setIndexes(array) {
    let counter = 0;
    array.forEach(subj => {
        subj.index = counter;
        counter++;
    });
}

function fetchAll(component) {
    crudFetch.get(URI_ALL)
        .then(data => {
            if (data || data.length !== 0) {
                setIndexes(data);
                component.setState({subjects: data})
            } else {
                component.setState({subjects: []})
            }
        })
        .catch(error => {
            this.setState({errorMessage: error.toString()})
        });

}

export default class Subjects extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            subjects: [],
            errorMessage: ""
        };
        this.updateState = this.updateState.bind(this);
        this.deleteEntry = this.deleteEntry.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.addSubject = this.addSubject.bind(this);
    }

    handleSubmit(e) {
        e.preventDefault();
        submitForm(this);
    }

    componentDidMount() {
        fetchAll(this);
    }

    updateState(updatedSubject) {
        let updatedSubjects = this.state.subjects;
        updatedSubjects.forEach((subj) => {
            if (subj.index === updatedSubject.index) {
                subj.name = updatedSubject.name;
            }
        });
        this.setState({subjects: updatedSubjects})
    }

    deleteEntry(subjToDelete) {
        console.log("delete in parent subjects");

        let updatedSubjects = [];
        this.state.subjects.map(subj => {
            if (subj.index!==subjToDelete.index){
                updatedSubjects.push(subj);
            }
        });
        // let index = updatedSubjects.indexOf(subjToDelete);
        // updatedSubjects.slice(index, 1);
        this.setState({subjects: updatedSubjects, errorMessage: "Subject deleted"});
        if (subjToDelete.subjectId && subjToDelete.subjectId > 0) {
            crudFetch.remove(URI, subjToDelete)
                .then(() => this.setState({errorMessage: "Subject deleted from DB"}))
                .catch((error) => this.setState({errorMessage: error.toString()}));
        }
    }

    addSubject() {
        let subjects = this.state.subjects;
        console.log(subjects);
        subjects.push({
            subjectId: null,
            name: "",
            index: subjects.length
        });
        console.log(subjects);
        this.setState({subjects: subjects});
    }

    render() {
        let parent = this;
        return (
            <div>
                <div className="row centered-form">
                    <div className="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
                        <div className="panel panel-default">
                            <div className="panel-heading">
                                <h3 className="panel-title">
                                    Subjects:
                                </h3>
                            </div>
                            <div className="panel-body">
                                <form onSubmit={this.handleSubmit}>
                                    {this.state.subjects.map(function (subject) {
                                        return <Subject key={'subject' + subject.index} subject={subject}
                                                        updateState={parent.updateState}
                                                        deleteEntry={parent.deleteEntry}/>
                                    })}
                                    <div className="row">
                                        <input type="button" value="A d d" className="btn btn-info btn-block"
                                               onClick={this.addSubject}/>
                                    </div>
                                    <br/>
                                    <div className="row">
                                        <input type="submit" value="S a v e" className="btn btn-info btn-block"/>
                                    </div>
                                </form>
                                <div className="row">
                                    <div className="col-xs-12 col-sm-12 col-md-12">
                                        <span style={{color: "red"}}>{this.state.errorMessage}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}
