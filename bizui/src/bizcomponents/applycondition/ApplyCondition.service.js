import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}applyConditionManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}applyConditionManager/loadApplyCondition/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidatePlatform = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}applyConditionManager/requestCandidatePlatform/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPlatform = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}applyConditionManager/transferToAnotherPlatform/id/anotherPlatformId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}







const addTargetUserRule = (targetObjectId, parameters) => {
  const url = `${PREFIX}applyConditionManager/addTargetUserRule/applyConditionId/name/parameter/promotionId/tokensExpr/`
  const applyConditionId = targetObjectId
  const requestParameters = { ...parameters, applyConditionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const updateTargetUserRule = (targetObjectId, parameters) => {
  const url = `${PREFIX}applyConditionManager/updateTargetUserRuleProperties/applyConditionId/id/name/parameter/tokensExpr/`
  const applyConditionId = targetObjectId
  const requestParameters = { ...parameters, applyConditionId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}

const removeTargetUserRuleList = (targetObjectId, parameters) => {
  const url = `${PREFIX}applyConditionManager/removeTargetUserRuleList/applyConditionId/targetUserRuleIds/tokensExpr/`
  const requestParameters = { ...parameters, applyConditionId: targetObjectId, tokensExpr: 'none' }
  return postForm({ url,requestParameters})
}


const ApplyConditionService = { view,
  load,
  addTargetUserRule,
  updateTargetUserRule,
  removeTargetUserRuleList,
  requestCandidatePlatform,
  transferToAnotherPlatform }
export default ApplyConditionService

