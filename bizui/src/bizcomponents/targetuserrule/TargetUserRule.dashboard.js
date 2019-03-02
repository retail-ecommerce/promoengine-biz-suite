

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
import styles from './TargetUserRule.dashboard.less'
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


const imageList =(targetUserRule)=>{return [
	 ]}

const internalImageListOf = (targetUserRule) =>defaultImageListOf(targetUserRule,imageList)

const optionList =(targetUserRule)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (targetUserRule) =>defaultSettingListOf(targetUserRule, optionList)
const internalLargeTextOf = (targetUserRule) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (targetUserRule,targetComponent) =>{
	
	
	const {TargetUserRuleService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="Id">{targetUserRule.id}</Description> 
<Description term="Name">{targetUserRule.name}</Description> 
<Description term="Apply Condition">{targetUserRule.applyCondition==null?appLocaleName(userContext,"NotAssigned"):targetUserRule.applyCondition.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Apply Condition","applyCondition",TargetUserRuleService.requestCandidateApplyCondition,
	      TargetUserRuleService.transferToAnotherApplyCondition,"anotherApplyConditionId",targetUserRule.applyCondition?targetUserRule.applyCondition.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
<Description term="Parameter">{targetUserRule.parameter}</Description> 
<Description term="Last Update Time">{ moment(targetUserRule.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
<Description term="Promotion">{targetUserRule.promotion==null?appLocaleName(userContext,"NotAssigned"):targetUserRule.promotion.displayName}
 <Icon type="swap" onClick={()=>
  showTransferModel(targetComponent,"Promotion","promotion",TargetUserRuleService.requestCandidatePromotion,
	      TargetUserRuleService.transferToAnotherPromotion,"anotherPromotionId",targetUserRule.promotion?targetUserRule.promotion.id:"")} 
  style={{fontSize: 20,color:"red"}} />
</Description>
	
        {buildTransferModal(targetUserRule,targetComponent)}
      </DescriptionList>
	)

}


class TargetUserRuleDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'targetUserRule'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName,  } = this.props.targetUserRule
    if(!this.props.targetUserRule.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"Target User Rule",cardsFor: "targetUserRule",
    	cardsSource: this.props.targetUserRule,returnURL,displayName,
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
  targetUserRule: state._targetUserRule,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(TargetUserRuleDashboard))

