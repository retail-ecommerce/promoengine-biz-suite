

import React, { Component } from 'react'
import FontAwesome from 'react-fontawesome';
import { connect } from 'dva'
import moment from 'moment'
import BooleanOption from 'components/BooleanOption';
import { Row, Col, Icon, Card, Tabs, Table, Radio, DatePicker, Tooltip, Menu, Dropdown,Badge, Switch,Select,Form,AutoComplete,Modal } from 'antd'
import { Link, Route, Redirect} from 'dva/router'
import numeral from 'numeral'
import {
  ChartCard, yuan, MiniArea, MiniBar, MiniProgress, Field, Bar, Pie, TimelineChart,
} from '../../components/Charts'
import Trend from '../../components/Trend'
import NumberInfo from '../../components/NumberInfo'
import { getTimeDistance } from '../../utils/utils'
import PageHeaderLayout from '../../layouts/PageHeaderLayout'
import styles from './TargetAction.dashboard.less'
import DescriptionList from '../../components/DescriptionList';
import ImagePreview from '../../components/ImagePreview';
import GlobalComponents from '../../custcomponents';
import DashboardTool from '../../common/Dashboard.tool'
import appLocaleName from '../../common/Locale.tool'

const {aggregateDataset,calcKey, defaultHideCloseTrans,
  defaultImageListOf,defaultSettingListOf,defaultBuildTransferModal,
  defaultExecuteTrans,defaultHandleTransferSearch,defaultShowTransferModel,
  defaultRenderExtraHeader,
  defaultSubListsOf,
  defaultRenderExtraFooter,renderForTimeLine,renderForNumbers
}= DashboardTool



const { Description } = DescriptionList;
const { TabPane } = Tabs
const { RangePicker } = DatePicker
const { Option } = Select


const imageList =(targetAction)=>{return [
	 ]}

const internalImageListOf = (targetAction) =>defaultImageListOf(targetAction,imageList)

const optionList =(targetAction)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (targetAction) =>defaultSettingListOf(targetAction, optionList)
const internalLargeTextOf = (targetAction) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (targetAction,targetComponent) =>{
	
	
	const {TargetActionService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="Id">{targetAction.id}</Description> 
<Description term="Name">{targetAction.name}</Description> 
<Description term="Action">{targetAction.action==null?appLocaleName(userContext,"NotAssigned"):targetAction.action.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Action","actionType",TargetActionService.requestCandidateAction,
	      TargetActionService.transferToAnotherAction,"anotherActionId",targetAction.action?targetAction.action.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Parameter">{targetAction.parameter}</Description> 
<Description term="Last Update Time">{ moment(targetAction.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
<Description term="Promotion">{targetAction.promotion==null?appLocaleName(userContext,"NotAssigned"):targetAction.promotion.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Promotion","promotion",TargetActionService.requestCandidatePromotion,
	      TargetActionService.transferToAnotherPromotion,"anotherPromotionId",targetAction.promotion?targetAction.promotion.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(targetAction,targetComponent)}
      </DescriptionList>
	)

}


class TargetActionDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'targetAction'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.targetAction
    if(!this.props.targetAction.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"Target Action",cardsFor: "targetAction",
    	cardsSource: this.props.targetAction,returnURL,displayName,
  		subItems: [
    
      	],
  	};
    
    const renderExtraHeader = this.props.renderExtraHeader || internalRenderExtraHeader
    const settingListOf = this.props.settingListOf || internalSettingListOf
    const imageListOf = this.props.imageListOf || internalImageListOf
    const subListsOf = this.props.subListsOf || internalSubListsOf
    const largeTextOf = this.props.largeTextOf ||internalLargeTextOf
    const summaryOf = this.props.summaryOf || internalSummaryOf
    const renderTitle = this.props.renderTitle || internalRenderTitle
    const renderExtraFooter = this.props.renderExtraFooter || internalRenderExtraFooter
    return (

      <PageHeaderLayout
        title={renderTitle(cardsData,this)}
        content={summaryOf(cardsData.cardsSource,this)}
        wrapperClassName={styles.advancedForm}
      >
      {renderExtraHeader(cardsData.cardsSource)}
        <div>
        {settingListOf(cardsData.cardsSource)}
        {imageListOf(cardsData.cardsSource)}
        {subListsOf(cardsData)} 
        {largeTextOf(cardsData.cardsSource)}
          
        </div>
      </PageHeaderLayout>
    )
  }
}

export default connect(state => ({
  targetAction: state._targetAction,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(TargetActionDashboard))

