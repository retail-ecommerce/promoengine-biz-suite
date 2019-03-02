import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}promotionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}promotionManager/loadPromotion/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}promotionManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}promotionManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTargetUserRule = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/addTargetUserRule/promotionId/name/applyConditionId/parameter/tokensExpr/`
  const promotionId = targetObjectId
  const requestParameters = { ...parameters, promotionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTargetUserRule = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/updateTargetUserRuleProperties/promotionId/id/name/parameter/tokensExpr/`
  const promotionId = targetObjectId
  const requestParameters = { ...parameters, promotionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTargetUserRuleList = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/removeTargetUserRuleList/promotionId/targetUserRuleIds/tokensExpr/`
  const requestParameters = { ...parameters, promotionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addTargetAction = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/addTargetAction/promotionId/name/actionId/parameter/tokensExpr/`
  const promotionId = targetObjectId
  const requestParameters = { ...parameters, promotionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTargetAction = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/updateTargetActionProperties/promotionId/id/name/parameter/tokensExpr/`
  const promotionId = targetObjectId
  const requestParameters = { ...parameters, promotionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTargetActionList = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/removeTargetActionList/promotionId/targetActionIds/tokensExpr/`
  const requestParameters = { ...parameters, promotionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}



const addPromotionOffer = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/addPromotionOffer/promotionId/name/promotionLevelId/parameter/tokensExpr/`
  const promotionId = targetObjectId
  const requestParameters = { ...parameters, promotionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updatePromotionOffer = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/updatePromotionOfferProperties/promotionId/id/name/parameter/tokensExpr/`
  const promotionId = targetObjectId
  const requestParameters = { ...parameters, promotionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removePromotionOfferList = (targetObjectId, parameters) => {
  const url = `${PREFIX}promotionManager/removePromotionOfferList/promotionId/promotionOfferIds/tokensExpr/`
  const requestParameters = { ...parameters, promotionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const PromotionService = { view,
  load,
  addTargetUserRule,
  addTargetAction,
  addPromotionOffer,
  updateTargetUserRule,
  updateTargetAction,
  updatePromotionOffer,
  removeTargetUserRuleList,
  removeTargetActionList,
  removePromotionOfferList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default PromotionService

