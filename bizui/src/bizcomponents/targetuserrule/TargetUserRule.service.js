import { get,postForm,PREFIX,joinParameters,joinPostParameters } from '../../axios/tools'


const view = (targetObjectId) => {
  return get({
    url: `${PREFIX}targetUserRuleManager/view/${targetObjectId}/`,
  })
}



const load = (targetObjectId, parameters) => {
  const parametersExpr = joinParameters(parameters)
  return get({
    url: `${PREFIX}targetUserRuleManager/loadTargetUserRule/${targetObjectId}/${parametersExpr}/`,
  })
}



const requestCandidateApplyCondition = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}targetUserRuleManager/requestCandidateApplyCondition/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherApplyCondition = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}targetUserRuleManager/transferToAnotherApplyCondition/id/anotherApplyConditionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}



const requestCandidatePromotion = (ownerClass, id, filterKey, pageNo) => {
 
  const url = `${PREFIX}targetUserRuleManager/requestCandidatePromotion/ownerClass/id/filterKey/pageNo/`
  const requestParameters = {id, ownerClass,filterKey, pageNo}
  return postForm({url,requestParameters})
}	

const transferToAnotherPromotion = (id, parameters) => {
  //const parametersExpr = joinParameters(parameters)
  const url = `${PREFIX}targetUserRuleManager/transferToAnotherPromotion/id/anotherPromotionId/`
  const requestParameters = {id, ...parameters}
  return postForm({url,requestParameters})
}






const TargetUserRuleService = { view,
  load,
  requestCandidateApplyCondition,
  requestCandidatePromotion,
  transferToAnotherApplyCondition,
  transferToAnotherPromotion }
export default TargetUserRuleService

