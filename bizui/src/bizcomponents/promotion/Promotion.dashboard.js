

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
import styles from './Promotion.dashboard.less'
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


const imageList =(promotion)=>{return [
	 ]}

const internalImageListOf = (promotion) =>defaultImageListOf(promotion,imageList)

const optionList =(promotion)=>{return [ 
	]}

const buildTransferModal = defaultBuildTransferModal
const showTransferModel = defaultShowTransferModel
const internalSettingListOf = (promotion) =>defaultSettingListOf(promotion, optionList)
const internalLargeTextOf = (promotion) =>{

	return null
	

}


const internalRenderExtraHeader = defaultRenderExtraHeader

const internalRenderExtraFooter = defaultRenderExtraFooter
const internalSubListsOf = defaultSubListsOf


const internalRenderTitle = (cardsData,targetComponent) =>{
  
  
  const linkComp=cardsData.returnURL?<Link to={cardsData.returnURL}> <FontAwesome name="arrow-left"  /> </Link>:null
  return (<div>{linkComp}{cardsData.cardsName}: {cardsData.displayName}</div>)

}


const internalSummaryOf = (promotion,targetComponent) =>{
	
	
	const {PromotionService} = GlobalComponents
	const userContext = null
	return (
	<DescriptionList className={styles.headerList} size="small" col="4">
<Description term="Id">{promotion.id}</Description> 
<Description term="Name">{promotion.name}</Description> 
<Description term="Valid After">{ moment(promotion.validAfter).format('YYYY-MM-DD')}</Description> 
<Description term="Expire Time">{ moment(promotion.expireTime).format('YYYY-MM-DD')}</Description> 
<Description term="Last Update Time">{ moment(promotion.lastUpdateTime).format('YYYY-MM-DD')}</Description> 
	
        {buildTransferModal(promotion,targetComponent)}
      </DescriptionList>
	)

}


class PromotionDashboard extends Component {

 state = {
    transferModalVisiable: false,
    candidateReferenceList: {},
    candidateServiceName:"",
    candidateObjectType:"city",
    targetLocalName:"",
    transferServiceName:"",
    currentValue:"",
    transferTargetParameterName:"",  
    defaultType: 'promotion'


  }
  componentDidMount() {

  }
  

  render() {
    // eslint-disable-next-line max-len
    const { id,displayName, targetUserRuleListMetaInfo, targetActionListMetaInfo, promotionOfferListMetaInfo, targetUserRuleCount, targetActionCount, promotionOfferCount } = this.props.promotion
    if(!this.props.promotion.class){
      return null
    }
    const returnURL = this.props.returnURL
    
    const cardsData = {cardsName:"Promotion",cardsFor: "promotion",
    	cardsSource: this.props.promotion,returnURL,displayName,
  		subItems: [
{name: 'targetUserRuleList', displayName:'Target User Rule',type:'targetUserRule',count:targetUserRuleCount,addFunction: true, role: 'targetUserRule', metaInfo: targetUserRuleListMetaInfo},
{name: 'targetActionList', displayName:'Target Action',type:'targetAction',count:targetActionCount,addFunction: true, role: 'targetAction', metaInfo: targetActionListMetaInfo},
{name: 'promotionOfferList', displayName:'Promotion Offer',type:'promotionOffer',count:promotionOfferCount,addFunction: true, role: 'promotionOffer', metaInfo: promotionOfferListMetaInfo},
    
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
  promotion: state._promotion,
  returnURL: state.breadcrumb.returnURL,
  
}))(Form.create()(PromotionDashboard))

