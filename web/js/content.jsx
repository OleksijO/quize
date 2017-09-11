let HelloWorld = React.createClass({
    render: function () {
        return <h5> My name is {this.props.name} and I love {this.props.action}! </h5>;
    }
});
ReactDOM.render(
    <div>
        <HelloWorld name="Nastya" action="eating"/>
        <HelloWorld name="Katya" action="football"/>
        <HelloWorld name="Vasya" action="walking"/>
        <HelloWorld name="Ivan" action="swimming"/>
    </div>,
    document.getElementById("content")
);