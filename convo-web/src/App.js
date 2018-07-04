import React, { Component } from 'react';
import { Message, Button, Accordion, Icon, Divider, Grid, Container, Image, Header} from 'semantic-ui-react';
import logo from './logo.svg';
import './App.css';
import Label from 'semantic-ui-react/dist/commonjs/elements/Label/Label';

//<img src={logo} className="App-logo" alt="logo" />

class App extends Component {
  state = { activeIndex: 4 }

  handleClick = (e, titleProps) => {
    const { index } = titleProps
    const { activeIndex } = this.state
    const newIndex = activeIndex === index ? -1 : index

    this.setState({ activeIndex: newIndex })
  }

  render() {
    const { activeIndex } = this.state

    return (
      <div className="App">
        <header className="App-header">
          <Grid>
          <Grid.Column floated='left' width={10}>
          <h1 className="App-title">
            Welcome Joe Bloggs    
          </h1>
          </Grid.Column>
          <Grid.Column floated='right' width={6}>
          <Image src='./patrick.png' avatar /> <Icon name='setting'/>
          </Grid.Column>
        </Grid>
        </header>
        <Header as='h3' block attached='top'>
          Available Focus Groups:
        </Header>
        <Accordion fluid attached>
        <Accordion.Title active={activeIndex === 0} index={0} onClick={this.handleClick}>
        <Grid>
        <Grid.Column floated='left' width={10}>
          <Icon name='dropdown' /> Smartphone Headphone Jacks 
        </Grid.Column>
        <Grid.Column floated='right' width={6}>
          <Label>
            <Icon name='users' /> 5
          </Label>
          <Label>
            $
          </Label>
        </Grid.Column>
      </Grid>
        </Accordion.Title>
        <Accordion.Content active={activeIndex === 0}>
          <p>
          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. 
          Aenean commodo ligula eget dolor. Aenean massa strong. 
          Cum sociis natoque penatibus et magnis dis parturient montes, 
          nascetur ridiculus mus... 
          </p>
          <Grid>
          <Grid.Column floated='right' width={8}>
          <Message size='tiny' positive>
            <p>
              Save 20% on ...
            </p>
          </Message>
          </Grid.Column>
          <Grid.Column floated='left' width={5}>
          <Button color='blue' content='Join' icon='right arrow' labelPosition='right' />
          </Grid.Column>
        </Grid>
        </Accordion.Content>
        <Divider fitted/>
        <Accordion.Title active={activeIndex === 1} index={1} onClick={this.handleClick}>
          <Grid>
            <Grid.Column floated='left' width={10}>
              <Icon name='dropdown' /> Cooking Podcasts
            </Grid.Column>
            <Grid.Column floated='right' width={6}>
              <Label>
                <Icon name='users' /> 23
              </Label>
              <Label>
              $$
              </Label>
            </Grid.Column>
          </Grid>
        </Accordion.Title>
        <Accordion.Content active={activeIndex === 1}>
          <p>
          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. 
          Aenean commodo ligula eget dolor. Aenean massa strong. 
          Cum sociis natoque penatibus et magnis dis parturient montes, 
          nascetur ridiculus mus... 
          </p>
          <Grid>
          <Grid.Column floated='right' width={8}>
          <Message size='tiny' positive>
            <p>
              Save 20% on ...
            </p>
          </Message>
          </Grid.Column>
          <Grid.Column floated='left' width={5}>
          <Button color='blue' content='Join' icon='right arrow' labelPosition='right' />
          </Grid.Column>
        </Grid>
        </Accordion.Content>
        <Divider fitted/>
        <Accordion.Title active={activeIndex === 2} index={2} onClick={this.handleClick}>
        <Grid>
        <Grid.Column floated='left' width={10}>
          <Icon name='dropdown' /> Robot Vacuum Cleaners
        </Grid.Column>
        <Grid.Column floated='right' width={6}>
          <Label>
            <Icon name='users' /> 15
          </Label>
          <Label>
            $
          </Label>
        </Grid.Column>
      </Grid>
        </Accordion.Title>
        <Accordion.Content active={activeIndex === 2}>
          <p>
          Lorem ipsum dolor sit amet, consectetuer adipiscing elit. 
          Aenean commodo ligula eget dolor. Aenean massa strong. 
          Cum sociis natoque penatibus et magnis dis parturient montes, 
          nascetur ridiculus mus... 
          </p>
          <Grid>
          <Grid.Column floated='right' width={8}>
          <Message size='tiny' positive>
            <p>
              Save 20% on ...
            </p>
          </Message>
          </Grid.Column>
          <Grid.Column floated='left' width={5}>
          <Button color='blue' content='Join' icon='right arrow' labelPosition='right' />
          </Grid.Column>
        </Grid>
        </Accordion.Content>
        <Divider fitted/>
      </Accordion>
      </div>
    );
  }
}

export default App;
